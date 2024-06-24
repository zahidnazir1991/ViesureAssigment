package com.viesure.assigment.api

import com.viesure.assigment.models.BookDetailsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.GET


interface ApiService {
    @GET("/")
    fun getBooks(): Deferred<Response<BookDetailsResponse>>

}