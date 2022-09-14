package com.sederikkuapplication.projects.proteo.data.repository.model.accounttokens

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ProteoListTokensRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val tokens: List<TokensRepositoryModel>
    ) : ProteoListTokensRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ProteoListTokensRepositoryResponseModel()
}