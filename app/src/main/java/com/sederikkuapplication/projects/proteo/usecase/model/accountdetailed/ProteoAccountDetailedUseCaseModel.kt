package com.sederikkuapplication.projects.proteo.usecase.model.accountdetailed

import com.example.common.network.GenericFailureCauseUseCaseModel

/**
 * Model used by Proteo Use Case
 */
sealed class ProteoAccountDetailedUseCaseModel {

    /**
     * If it's Success
     */
    data class Success(
        val accountDetailed: AccountDetailedUseCaseModel
    ) : ProteoAccountDetailedUseCaseModel()

    /**
     * If it's Failure
     */
    class GenericFailure(val cause: GenericFailureCauseUseCaseModel) :
        ProteoAccountDetailedUseCaseModel()
}