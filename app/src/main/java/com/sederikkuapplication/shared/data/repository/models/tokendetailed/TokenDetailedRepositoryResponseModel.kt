package com.sederikkuapplication.shared.data.repository.models.tokendetailed

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Repository
 */
sealed class TokenDetailedRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val tokenDetailed: TokenDetailedRepositoryModel
    ) : TokenDetailedRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : TokenDetailedRepositoryResponseModel()

}