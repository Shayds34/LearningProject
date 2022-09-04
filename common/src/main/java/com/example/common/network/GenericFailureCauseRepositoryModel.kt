package com.example.common.network

/**
 * Generic error that can be used in any repository response
 */
sealed class GenericFailureCauseRepositoryModel {

    /**
     * We don't know the error source
     */
    data class UNKNOWN(val sourceThrowable: Throwable) : GenericFailureCauseRepositoryModel()

    /**
     * It's because we did not have internet to perform the call
     */
    data class NO_INTERNET(val sourceThrowable: Throwable) : GenericFailureCauseRepositoryModel()
}
