package com.sederikkuapplication.shared.data.network.models

data class Argument (
    val transfers: List<Transfer>,
    val receiver: String,
    val functionName: String
)
