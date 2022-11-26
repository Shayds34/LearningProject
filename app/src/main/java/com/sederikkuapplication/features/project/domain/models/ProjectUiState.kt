package com.sederikkuapplication.features.project.domain.models

import com.sederikkuapplication.shared.domain.models.TokenDetailed
import com.sederikkuapplication.shared.domain.models.TokenTeamAccount

data class ProjectUiState(
    val isLoading: Boolean = false,
    val token: TokenDetailed? = null,
    val lockedAccounts : List<TokenTeamAccount> = emptyList()
)