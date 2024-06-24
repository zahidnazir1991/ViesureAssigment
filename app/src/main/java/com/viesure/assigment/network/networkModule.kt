package com.viesure.assigment.network

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.viesure.assigment.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.d("ApiCalls", "-- $message --")
        }
    }).apply {
        level = HttpLoggingInterceptor.Level.BODY

    }

    @Provides
    @Singleton
    fun provideRetryInterceptor(): RetryInterceptor {
        return RetryInterceptor(maxRetries = 3, retryDelayMillis = 2000)
    }


    @Provides
    @Singleton
    fun provideRetrofit(retryInterceptor: RetryInterceptor): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            //.addInterceptor(retryInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://c27b2d72-8d9c-4aa0-b549-7ae7e5666815.mock.pstmn.io/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}