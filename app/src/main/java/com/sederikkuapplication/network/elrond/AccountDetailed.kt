package com.sederikkuapplication.network.elrond

// non exhaustive liste
// https://api.elrond.com/#/accounts/AccountController_getAccountDetails
data class AccountDetailed(
    val address: String,
    val balance: String,
    val nonce: Number,
    val shard: Number,
    val username: String?
)