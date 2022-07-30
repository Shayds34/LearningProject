package com.sederikkuapplication.proteo.usecase.model.tokenlist

import com.sederikkuapplication.network.elrond.TokenWithBalance

data class ListTokensUseCaseModel(
    val tokens: List<TokenWithBalance>
)
