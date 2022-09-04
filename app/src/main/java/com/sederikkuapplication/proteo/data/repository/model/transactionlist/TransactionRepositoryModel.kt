package com.sederikkuapplication.proteo.data.repository.model.transactionlist

import com.sederikkuapplication.network.elrond.transaction.Action
import com.sederikkuapplication.network.elrond.transaction.Operation

/**
 * Response model used by Proteo Repository
 */
data class TransactionRepositoryModel(
    val sender: String,
    val timestamp: Number,
    val value: String,
    val function: String,
    val action: Action,
    val operations: List<Operation>?
)
