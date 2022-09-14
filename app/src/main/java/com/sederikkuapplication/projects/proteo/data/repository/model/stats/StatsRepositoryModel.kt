package com.sederikkuapplication.projects.proteo.data.repository.model.stats

/**
 * Response model used by Proteo Repository
 */
data class StatsRepositoryModel(
    val accounts: Number,
    val blocks: Number,
    val epoch: Number,
    val refreshRate: Number,
    val roundsPassed: Number,
    val roundsPerEpoch: Number,
    val shards: Number,
    val transactions: Number
)
