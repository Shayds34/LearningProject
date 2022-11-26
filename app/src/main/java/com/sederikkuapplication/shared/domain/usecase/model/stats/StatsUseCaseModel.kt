package com.sederikkuapplication.shared.domain.usecase.model.stats

data class StatsUseCaseModel(
    val accounts: Number,
    val blocks: Number,
    val epoch: Number,
    val refreshRate: Number,
    val roundsPassed: Number,
    val roundsPerEpoch: Number,
    val shards: Number,
    val transactions: Number
)
