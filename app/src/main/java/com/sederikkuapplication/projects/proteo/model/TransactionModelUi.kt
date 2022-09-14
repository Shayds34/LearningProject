package com.sederikkuapplication.projects.proteo.model

import com.sederikkuapplication.network.elrond.transaction.Action

data class TransactionModelUi constructor(
    val data: Data
) {

    data class Data(
        val sender: String,
        val timestamp: Number,
        val epoch: String, // Add with mapping
        val function: String,
        val action: Action,
        val token: String, // Add with mapping
        val shortenErd: String // Add with mapping
    )
}