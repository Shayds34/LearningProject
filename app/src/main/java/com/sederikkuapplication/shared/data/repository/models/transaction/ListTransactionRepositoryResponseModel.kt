package com.sederikkuapplication.shared.data.repository.models.transaction

import com.example.common.network.GenericFailureCauseRepositoryModel

/**
 * Response model used by Proteo Repository
 */
sealed class ListTransactionRepositoryResponseModel {

    /**
     * If it's success
     */
    data class Success(
        val transactions: List<TransactionRepositoryModel>
    ) : ListTransactionRepositoryResponseModel()

    /**
     * If it's failure
     */
    data class GenericFailure(
        val cause: GenericFailureCauseRepositoryModel
    ) : ListTransactionRepositoryResponseModel()
}