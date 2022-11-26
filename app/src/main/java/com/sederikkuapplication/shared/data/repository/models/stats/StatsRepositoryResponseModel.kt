package com.sederikkuapplication.shared.data.repository.models.stats

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Elrond Repository
 */
sealed class StatsRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val stats: StatsRepositoryModel
    ) : StatsRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : StatsRepositoryResponseModel()

}