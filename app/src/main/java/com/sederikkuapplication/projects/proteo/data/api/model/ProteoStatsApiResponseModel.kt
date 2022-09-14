package com.sederikkuapplication.projects.proteo.data.api.model

class ProteoStatsApiResponseModel(
    val accounts: Number,
    val blocks: Number,
    val epoch: Number,
    val refreshRate: Number,
    val roundsPassed: Number,
    val roundsPerEpoch: Number,
    val shards: Number,
    val transactions: Number
)