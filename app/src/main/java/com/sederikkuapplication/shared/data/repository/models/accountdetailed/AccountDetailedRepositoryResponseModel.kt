package com.sederikkuapplication.shared.data.repository.models.accountdetailed

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class AccountDetailedRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val accountDetailed: AccountDetailedRepositoryModel
    ) : AccountDetailedRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : AccountDetailedRepositoryResponseModel()
}