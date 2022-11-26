package com.sederikkuapplication.shared.data.repository.models.asset

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * ASSET RESPONSE MODEL used by Repository
 *
 */
sealed class AssetRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val asset: AssetRepositoryModel
    ) : AssetRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : AssetRepositoryResponseModel()

}