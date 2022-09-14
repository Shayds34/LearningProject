package com.sederikkuapplication.projects.proteo.data.api.model

class ProteoTokensApiResponseModel(
    val identifier: String,
    val name: String,
    val ticker: String,
    val decimals: String,
    val price: Number?,
    val marketcap: Number?,
    val supply: String?,
    val circulatingSupply: String,
    val balance: String,
    val valueUsd: String?
)