package com.sederikkuapplication.shared.data.network.responsemodels

/**
 * ECONOMICS API RESPONSE MODEL
 */
data class EconomicsApiResponseModel(
    val totalSupply: Number,
    val circulatingSupply: Number,
    val staked: Number,
    val price: Number,
    val marketCap: Number,
    val apr: Number,
    val topUpApr: Number,
    val baseApr: Number,
    val minimumAuctionTopUp: String?,
    val tokenMarketCap: Number?
)