package com.viesure.assigment.network

import okhttp3.Interceptor
import okhttp3.Response

import java.io.IOException

class RetryInterceptor(private val maxRetries: Int, private val retryDelayMillis: Long) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        var attempt = 0

        while (attempt < maxRetries) {
            try {
                response = chain.proceed(chain.request())
                if (response.isSuccessful) return response
            } catch (e: Exception) {
                attempt++
                if (attempt == maxRetries) throw e
                Thread.sleep(retryDelayMillis)
            }
        }
        throw IOException("Max retries reached")
    }
}