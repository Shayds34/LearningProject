package com.sederikkuapplication.shared.data.repository.models.economics

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Repository
 */
sealed class EconomicsRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val economics: EconomicsRepositoryModel
    ) : EconomicsRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : EconomicsRepositoryResponseModel()

}