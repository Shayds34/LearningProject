package com.sederikkuapplication.proteo.data.repository.model.accountdetailed

/**
 * Response model used by Proteo Repository
 */
data class AccountDetailedRepositoryModel(
    val address: String,
    val balance: String,
    val nonce: Number,
    val shard: Number,
    val username: String?
)
