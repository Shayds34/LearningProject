package com.sederikkuapplication.shared.domain.usecase.model.transactionlist

import com.example.common.network.GenericFailureCauseUseCaseModel
import com.sederikkuapplication.shared.data.network.models.Transaction

/**
 * Model used by Proteo Use Case
 */
sealed class ProteoListTransactionsUseCaseModel {

    /**
     * If it's Success
     */
    data class Success(
        val transactions: List<Transaction>
    ) : ProteoListTransactionsUseCaseModel()

    /**
     * If it's Failure
     */
    class GenericFailure(val cause: GenericFailureCauseUseCaseModel) :
        ProteoListTransactionsUseCaseModel()
}