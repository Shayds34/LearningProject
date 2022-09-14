package com.sederikkuapplication.projects.proteo.usecase.model.tokendetailed

import com.example.common.network.GenericFailureCauseUseCaseModel

/**
 * Model used by Proteo Use Case
 */
sealed class ProteoTokenDetailedUseCaseModel {

    /**
     * If it's Success
     */
    data class Success(
        val tokenDetailed: TokenDetailedUseCaseModel
    ) : ProteoTokenDetailedUseCaseModel()

    /**
     * If it's Failure
     */
    class GenericFailure(val cause: GenericFailureCauseUseCaseModel) :
        ProteoTokenDetailedUseCaseModel()
}