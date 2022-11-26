package com.sederikkuapplication.shared.data.repository.models.asset

data class AssetRepositoryModel(
    val website: String?,
    val description: String?,
    val ledgerSignature: String?,
    // social
    val status: String = "inactive", // Enum [ active, inactive ]
    val lockedAccounts: Unit?,
    val extraTokens: List<String>?,
    val pngUrl: String?,
    val svgUrl: String?
)