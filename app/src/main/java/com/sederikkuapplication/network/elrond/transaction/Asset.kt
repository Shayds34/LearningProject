package com.sederikkuapplication.network.elrond.transaction

import com.sederikkuapplication.network.elrond.TokenDetailed

data class Asset(
    val website: String,
    val description: String,
    val status: String = "inactive", // enum [ active, inactive ]
    val pngUrl: String,
    val svgUrl: String,
    val lockedAccounts: Unit?,
    val extraTokens: List<String>?
)