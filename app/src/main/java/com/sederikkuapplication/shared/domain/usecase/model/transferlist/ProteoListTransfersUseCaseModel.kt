package com.sederikkuapplication.shared.domain.usecase.model.transferlist

import com.example.common.network.GenericFailureCauseUseCaseModel
import com.sederikkuapplication.shared.data.network.models.Transaction

/**
 * Model used by Proteo Use Case
 */
sealed class ProteoListTransfersUseCaseModel {

    /**
     * If it's Success
     */
    data class Success(
        val transfers: List<Transaction>
    ) : ProteoListTransfersUseCaseModel()

    /**
     * If it's Failure
     */
    class GenericFailure(val cause: GenericFailureCauseUseCaseModel) :
        ProteoListTransfersUseCaseModel()
}