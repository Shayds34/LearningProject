package com.sederikkuapplication.projects.proteo.model

import com.sederikkuapplication.network.elrond.transaction.Transaction

data class ProteoUnstakeModelUi constructor(
    val transaction: Transaction,
    // Calculate with mapping
    val shortenAddress: String,
    val epoch: String,
    val sProteo: String = "0",
    var isWithdraw: Boolean = false
)