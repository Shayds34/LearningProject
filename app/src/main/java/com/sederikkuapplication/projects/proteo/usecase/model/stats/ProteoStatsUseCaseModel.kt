package com.sederikkuapplication.projects.proteo.usecase.model.stats

import com.example.common.network.GenericFailureCauseUseCaseModel

/**
 * Model used by Proteo Use Case
 */
sealed class ProteoStatsUseCaseModel {

    /**
     * If it's Success
     */
    data class Success(
        val stats: StatsUseCaseModel
    ) : ProteoStatsUseCaseModel()

    /**
     * If it's Failure
     */
    class GenericFailure(val cause: GenericFailureCauseUseCaseModel) :
        ProteoStatsUseCaseModel()
}