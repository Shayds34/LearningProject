package com.sederikkuapplication.shared.data.repository.models.lockedaccount

import com.example.common.network.GenericFailureCauseRepositoryModel

sealed interface LockedAccountRepositoryResponseModel {


    /**
     * If it's success
     */
    data class Success(
        val role: LockedAccountRepositoryModel
    ) : LockedAccountRepositoryResponseModel

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : LockedAccountRepositoryResponseModel


}