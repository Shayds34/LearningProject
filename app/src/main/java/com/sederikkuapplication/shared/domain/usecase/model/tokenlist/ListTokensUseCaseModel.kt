package com.sederikkuapplication.shared.domain.usecase.model.tokenlist

import com.sederikkuapplication.shared.data.network.responsemodels.TokenWithBalance

data class ListTokensUseCaseModel(
    val tokens: List<TokenWithBalance>
)
