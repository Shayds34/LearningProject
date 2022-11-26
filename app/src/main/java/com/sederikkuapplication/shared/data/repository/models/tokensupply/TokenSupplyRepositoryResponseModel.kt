package com.sederikkuapplication.shared.data.repository.models.tokensupply

import com.example.common.network.GenericFailureCauseRepositoryModel

sealed class TokenSupplyRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val tokenSupply: TokenSupplyRepositoryModel
    ) : TokenSupplyRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : TokenSupplyRepositoryResponseModel()


}
