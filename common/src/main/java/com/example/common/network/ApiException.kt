package com.example.common.network

sealed class ApiException : Throwable() {

    data class Default(
        internal val httpCode: Int? = null,
        val sourceThrowable: Throwable,
        val errorBody: BaseErrorBody<*>? = null,
        val errorBodyString: String? = null
    ) : ApiException()

    data class NoInternet(
        val sourceThrowable: Throwable
    ) : ApiException()
}