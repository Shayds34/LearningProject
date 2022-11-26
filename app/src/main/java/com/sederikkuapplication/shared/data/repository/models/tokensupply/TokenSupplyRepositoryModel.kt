package com.sederikkuapplication.shared.data.repository.models.tokensupply

import com.sederikkuapplication.shared.data.repository.models.lockedaccount.LockedAccountRepositoryModel

data class TokenSupplyRepositoryModel(
    val supply: String,
    val circulatingSupply: String,
    val minted: String,
    val burnt: String,
    val initialMinted: String,
    val lockedAccounts: List<LockedAccountRepositoryModel>
)