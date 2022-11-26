package com.sederikkuapplication.shared.data.repository.models.tokendetailed

import com.sederikkuapplication.shared.data.repository.models.asset.AssetRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.role.RoleRepositoryModel

/**
 * TOKEN DETAILED REPOSITORY MODEL
 *
 * - [minted] : Minted amount
 * - [burnt] : Burnt amount
 * - [initialMinted] : Initial Minted amount
 * - [supply] : Supply amount
 * - [circulatingSupply] : Circulating supply amount
 */
data class TokenDetailedRepositoryModel(
    val identifier: String,
    val name: String,
    val ticker: String,
    val owner: String,
    val minted: String?,
    val burnt: String?,
    val initialMinted: String?,
    val decimals: Number,
    val isPaused: Boolean = false,
    val assets: AssetRepositoryModel?,
    val transactions: Number?,
    val accounts: Number?,
    val canUpgrade: Boolean = false,
    val canMint: Boolean = false,
    val canBurn: Boolean = false,
    val canChangeOwner: Boolean = false,
    val canPause: Boolean = false,
    val canFreeze: Boolean = false,
    val canWipe: Boolean = false,
    val price: Number?,
    val marketCap: Number?,
    val supply: String?,
    val circulatingSupply: String?,
    val role: List<RoleRepositoryModel>?
)
