package com.viesure.assigment.api

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T?, val statusCode: Int) : ApiResult<T>()
    data class Error(
        val exception: Throwable,
        val statusCode: Int? = null,
        val message: String? = null
    ) : ApiResult<Nothing>()
}