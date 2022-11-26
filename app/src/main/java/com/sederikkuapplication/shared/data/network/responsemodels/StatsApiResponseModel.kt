package com.sederikkuapplication.shared.data.network.responsemodels

/**
 * STATS MODEL FROM ELROND API
 */
data class StatsApiResponseModel(
    val accounts: Number,
    val blocks: Number,
    val epoch: Number,
    val refreshRate: Number,
    val roundsPassed: Number,
    val roundsPerEpoch: Number,
    val shards: Number,
    val transactions: Number
)