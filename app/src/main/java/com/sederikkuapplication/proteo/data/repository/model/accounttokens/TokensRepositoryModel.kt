package com.sederikkuapplication.proteo.data.repository.model.accounttokens

/**
 * Response model used by Proteo Repository
 */
data class TokensRepositoryModel(
    val identifier: String,
    val name: String,
    val ticker: String,
    val decimals: String,
    val price: Number?,
    val marketcap: Number?,
    val supply: String?,
    val circulatingSupply: String?,
    val balance: String,
    val valueUsd: String?
)
