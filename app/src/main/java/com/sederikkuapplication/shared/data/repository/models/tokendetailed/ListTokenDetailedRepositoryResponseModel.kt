package com.sederikkuapplication.shared.data.repository.models.tokendetailed

import com.example.common.network.GenericFailureCauseRepositoryModel

sealed class ListTokenDetailedRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val tokens: List<TokenDetailedRepositoryModel>
    ) : ListTokenDetailedRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ListTokenDetailedRepositoryResponseModel()

}