package com.sederikkuapplication.network.elrond

import com.sederikkuapplication.network.elrond.transaction.Asset
import com.sederikkuapplication.network.elrond.transaction.Role

data class TokenDetailed(
    val identifier: String,
    val name: String,
    val ticker: String,
    val owner: String,
    val minted: String,
    val burnt: String,
    val initialMinted: String,
    val decimals: Number,
    val isPaused: Boolean,
    val assets: Asset?,
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
    val supply: String,
    val circulatingSupply: String,
    val roles: List<Role>?
    )
