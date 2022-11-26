package com.sederikkuapplication.shared.domain.usecases

import javax.inject.Inject

/**
 * All UseCases related to Tokens
 * - [getTeamAccounts] : get all accounts infos, owned by the Team
 * - [getTokenDetailed] : get details from a given identifier
 */
data class TokensUseCases @Inject constructor(
    val getTeamAccounts: TeamAccountsUseCase,
    val getTokens: TokensUseCase,
    val getTokenDetailed: TokenDetailsUseCase
)