package com.sederikkuapplication.proteo.data.repository.model.tokendetailed

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ProteoTokenDetailedRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val tokenDetailed: TokenDetailedRepositoryModel
    ) : ProteoTokenDetailedRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ProteoTokenDetailedRepositoryResponseModel()
}