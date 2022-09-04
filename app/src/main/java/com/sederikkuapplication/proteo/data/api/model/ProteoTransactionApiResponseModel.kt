package com.sederikkuapplication.proteo.data.api.model

import com.sederikkuapplication.network.elrond.transaction.Action
import com.sederikkuapplication.network.elrond.transaction.Operation

class ProteoTransactionApiResponseModel(
    val sender: String,
    val timestamp: Number,
    val value: String,
    val function: String,
    val action: Action,
    val operations: List<Operation>?
)