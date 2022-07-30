package com.example.common.network

import android.util.Log
import com.example.common.network.ApiException.Default
import java.net.SocketTimeoutException

typealias NetworkResponse<T> = Response<T, ApiException>

suspend fun <T> safeApiCall(call: suspend () -> T): NetworkResponse<T> {
    return try {
        safeApiCallThrowable(call).asResponseSuccess()
    } catch (t: ApiException) {
        Log.e("ApiException", t.toString())
        t.asResponseFailure()
    } catch (t: Throwable) {
        Log.e("HTTP safeApiCall", t.toString())
        Default(
            sourceThrowable = t,
            errorBodyString = null,
        ).asResponseFailure()
    }
}

@Throws(ApiException::class)
private suspend fun <T> safeApiCallThrowable(call: suspend () -> T): T {
    val t = try {
        val result = call.invoke()
        Log.d(
            "SafeApiCall",
            "safe Api Call Throwable is retrofit2: ${result is retrofit2.Response<*>}"
        )
        result
    } catch (throwable: Throwable) {
        when (throwable) {
            is SocketTimeoutException -> {
                throw throwable.parseToNoInternet()
            }
            is ApiException -> {
                throw throwable
            }
            else -> {
                throw throwable.parseToDefault()
            }
        }
    }
    return t
}

private fun SocketTimeoutException.parseToNoInternet() = ApiException.NoInternet(
    sourceThrowable = this
)

private fun Throwable.parseToDefault() = Default(
    sourceThrowable = this,
    errorBodyString = null
)
