package com.sederikkuapplication.shared.data.network.responsemodels

import com.sederikkuapplication.shared.data.network.models.LockedAccountApiModel

data class TokenSupplyApiResponseModel(
    val supply: String,
    val circulatingSupply: String,
    val minted: String,
    val burnt: String,
    val initialMinted: String,
    val lockedAccounts: List<LockedAccountApiModel>
)