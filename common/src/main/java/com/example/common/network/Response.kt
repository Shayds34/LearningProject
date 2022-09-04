package com.example.common.network

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
fun <SUCCESS> isSuccess(response: Response<SUCCESS, *>): Boolean {
    contract {
        returns(true) implies (response is Response.Success<SUCCESS>)
    }
    return (response is Response.Success<SUCCESS>)
}

@ExperimentalContracts
fun <FAILURE> isFailure(response: Response<*, FAILURE>): Boolean {
    contract {
        returns(true) implies (response is Response.Failure<FAILURE>)
    }
    return (response is Response.Failure<FAILURE>)
}

sealed class Response<out SUCCESS, out FAILURE>() {
    data class Success<out SUCCESS>(val value: SUCCESS) : Response<SUCCESS, Nothing>()
    data class Failure<out FAILURE>(val cause: FAILURE) : Response<Nothing, FAILURE>()

    suspend fun onFailure(block: suspend (FAILURE) -> Unit): Response<out SUCCESS, out FAILURE> {
        if (this is Failure<FAILURE>) {
            block(cause)
        }
        return this
    }

    suspend fun onSuccess(block: suspend (SUCCESS) -> Unit): Response<out SUCCESS, out FAILURE> {
        if (this is Success<SUCCESS>) {
            block(value)
        }
        return this
    }

    suspend fun <OUT_SUCCESS, OUT_FAILURE> mapToResponse(
        success: suspend (SUCCESS) -> OUT_SUCCESS,
        failure: suspend (FAILURE) -> OUT_FAILURE
    ): Response<OUT_SUCCESS, OUT_FAILURE> {
        return when (this) {
            is Success -> success(this.value).asResponseSuccess()
            is Failure -> failure(this.cause).asResponseFailure()
        }
    }

    suspend fun <OUT_SUCCESS, OUT_FAILURE> map(
        response: suspend (Response<SUCCESS, FAILURE>) -> Response<OUT_SUCCESS, OUT_FAILURE>
    ): Response<OUT_SUCCESS, OUT_FAILURE> {
        return response(this)
    }

    suspend fun <T> mapToSingleType(
        success: suspend (SUCCESS) -> T,
        failure: suspend (FAILURE) -> T
    ): T {
        return when (this) {
            is Success -> success(this.value)
            is Failure -> failure(this.cause)
        }
    }


    fun <T> mapSuccess(block: (SUCCESS) -> T): T {
        return block(successValue)
    }

    fun <T> mapSuccessToResponse(block: (SUCCESS) -> T): Response<T, *> {
        return mapSuccess(block).asResponseSuccess()
    }

    fun <T> mapFailure(block: (FAILURE) -> T): T {
        return block(errorValue)
    }

    fun <T> mapFailureToResponse(block: (FAILURE) -> T): Response<T, *> {
        return mapFailure(block).asResponseFailure()
    }

    fun isSuccess(): Boolean {
        return (this is Success<SUCCESS>)
    }

    fun isFailure(): Boolean {
        return (this is Failure<FAILURE>)
    }

    val errorValue: FAILURE
        get() {
            when (this) {
                is Failure<FAILURE> -> {
                    return this.cause
                }
                else -> {
                    throw NotImplementedError()
                }
            }
        }

    val successValue: SUCCESS
        get() {
            when (this) {
                is Success<SUCCESS> -> {
                    return this.value
                }
                else -> {
                    throw NotImplementedError()
                }
            }
        }

    companion object {
        fun <T> success(value: T) = Success(value)
        fun <T> failure(value: T) = Failure(value)
    }
}

fun <T> T.asResponseSuccess() = Response.success(this)
fun <T> T.asResponseFailure() = Response.failure(this)

