package com.sederikkuapplication.projects.proteo.data.repository.model.transactionlist

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ProteoTransactionsRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val transactions: List<TransactionRepositoryModel>
    ) : ProteoTransactionsRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ProteoTransactionsRepositoryResponseModel()
}