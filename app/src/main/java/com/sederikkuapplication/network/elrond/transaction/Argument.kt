package com.sederikkuapplication.network.elrond.transaction

data class Argument(
    val transfers: List<Transfer>,
    val receiver: String,
    val functionName: String
)
