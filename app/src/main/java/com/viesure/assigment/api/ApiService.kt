package com.viesure.assigment.api

import com.viesure.assigment.models.BookDetailsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.GET


interface ApiService {
    //@GET("/")
    @GET("https://run.mocky.io/v3/eebcc40c-4599-4f05-bdf8-1307e63b6072")
    fun getBooks(): Deferred<Response<BookDetailsResponse>>

}