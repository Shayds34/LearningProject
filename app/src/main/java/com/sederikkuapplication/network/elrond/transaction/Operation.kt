package com.sederikkuapplication.network.elrond.transaction

data class Operation (
        val id: String,
        val action: String,
        val type: String,
        val sender: String,
        val receiver: String,
        val value: String,
        val decimals: Number
)