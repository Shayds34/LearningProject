package com.sederikkuapplication.network.network

data class Stats(
    val accounts: Number,
    val blocks: Number,
    val epoch: Number,
    val refreshRate: Number,
    val roundsPassed: Number,
    val roundsPerEpoch: Number,
    val shards: Number,
    val transactions: Number
)