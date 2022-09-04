package com.sederikkuapplication.network.elrond.transaction

/**
 * Data model for Transaction
 */
data class Transaction (
    val sender: String,
    val timestamp: Number,
    val value: String,
    val function: String,
    val action: Action,
    val operations: List<Operation>?
)