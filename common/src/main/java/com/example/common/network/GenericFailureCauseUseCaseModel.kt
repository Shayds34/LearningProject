package com.example.common.network

sealed class GenericFailureCauseUseCaseModel {
    data class NO_INTERNET(val sourceThrowable: Throwable) : GenericFailureCauseUseCaseModel() {
        override fun equals(other: Any?): Boolean {
            return other is NO_INTERNET
        }

        override fun hashCode(): Int {
            return sourceThrowable.hashCode()
        }
    }

    data class UNKNOWN(val sourceThrowable: Throwable) : GenericFailureCauseUseCaseModel() {
        override fun equals(other: Any?): Boolean {
            return other is UNKNOWN
        }

        override fun hashCode(): Int {
            return sourceThrowable.hashCode()
        }
    }
}