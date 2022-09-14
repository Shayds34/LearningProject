package com.sederikkuapplication.projects.proteo.data.repository.model.transferlist

import com.sederikkuapplication.network.elrond.transaction.Action
import com.sederikkuapplication.network.elrond.transaction.Operation

/**
 * Response model used by Proteo Repository
 */
data class TransfersRepositoryModel(
    val sender: String,
    val timestamp: Number,
    val value: String,
    val function: String,
    val action: Action,
    val operations: List<Operation>?
)
