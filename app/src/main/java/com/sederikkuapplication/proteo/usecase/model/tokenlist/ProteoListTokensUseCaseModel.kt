package com.sederikkuapplication.proteo.usecase.model.tokenlist

import com.example.common.network.GenericFailureCauseUseCaseModel
import com.sederikkuapplication.network.elrond.TokenWithBalance

/**
 * Model used by Proteo Use Case
 */
sealed class ProteoListTokensUseCaseModel {

    /**
     * If it's Success
     */
    data class Success(
        val tokens: List<TokenWithBalance>
    ) : ProteoListTokensUseCaseModel()

    /**
     * If it's Failure
     */
    class GenericFailure(val cause: GenericFailureCauseUseCaseModel) :
        ProteoListTokensUseCaseModel()
}