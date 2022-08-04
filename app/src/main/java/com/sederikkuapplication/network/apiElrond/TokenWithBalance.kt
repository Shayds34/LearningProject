package com.sederikkuapplication.network.apiElrond

data class TokenWithBalance(
    val identifier: String,
    val decimals: String,
    val balance: String,
    val valueUsd: String
)
