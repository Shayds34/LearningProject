package com.sederikkuapplication.projects.proteo.data.api.model

import com.sederikkuapplication.network.elrond.transaction.Action
import com.sederikkuapplication.network.elrond.transaction.Operation

class ProteoTransferApiResponseModel(
    val sender: String,
    val timestamp: Number,
    val value: String,
    val function: String,
    val action: Action,
    val operations: List<Operation>?
)