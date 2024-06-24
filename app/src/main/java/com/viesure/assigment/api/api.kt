package com.viesure.assigment.api
import com.viesure.assigment.api.ApiResult.Success
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.io.IOException

suspend fun <T> performApiCall(call: Deferred<Response<T>>): ApiResult<T> {
    return try {
        val response = call.await()
        if (response.isSuccessful && response.body() != null) {
            Success(response.body(), response.code())
        } else {
            val errorBody = response.errorBody()?.string()
            if (errorBody?.contains("usageLimitError") == true) {
                ApiResult.Error(
                    IOException("Usage limit reached. Your team plan allows 1000 mock server calls per month."),
                    response.code(),
                    "Usage limit reached"
                )
            } else {
                ApiResult.Error(
                    IOException("Error occurred during fetching data"),
                    response.code(),
                    response.message()
                )
            }
        }
    } catch (e: IOException) {
        ApiResult.Error(e, message = e.message)
    } catch (e: Exception) {
        ApiResult.Error(e, message = e.message)
    }
}