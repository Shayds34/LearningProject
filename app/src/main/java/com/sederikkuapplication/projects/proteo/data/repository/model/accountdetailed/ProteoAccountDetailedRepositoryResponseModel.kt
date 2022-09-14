package com.sederikkuapplication.projects.proteo.data.repository.model.accountdetailed

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ProteoAccountDetailedRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val accountDetailed: AccountDetailedRepositoryModel
    ) : ProteoAccountDetailedRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ProteoAccountDetailedRepositoryResponseModel()
}