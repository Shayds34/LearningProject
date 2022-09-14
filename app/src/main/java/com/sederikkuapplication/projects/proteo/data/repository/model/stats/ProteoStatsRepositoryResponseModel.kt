package com.sederikkuapplication.projects.proteo.data.repository.model.stats

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ProteoStatsRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val stats: StatsRepositoryModel
    ) : ProteoStatsRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ProteoStatsRepositoryResponseModel()
}