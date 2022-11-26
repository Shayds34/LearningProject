package com.sederikkuapplication.shared.domain.usecases

import com.sederikkuapplication.shared.utils.balanceWithDecimals
import com.sederikkuapplication.shared.utils.shortenErd
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokensupply.TokenSupplyRepositoryResponseModel
import com.sederikkuapplication.shared.domain.models.TokenTeamAccount
import com.sederikkuapplication.shared.domain.repository.TokensRepository
import javax.inject.Inject

class TeamAccountsUseCase @Inject constructor(
    private val repository: TokensRepository
) {
    suspend operator fun invoke(
        identifier: String
    ) : List<TokenTeamAccount> {
        val result = repository.getTokenSupply(identifier)
        val token = repository.getToken(identifier)
        when (result) {
            is TokenSupplyRepositoryResponseModel.Success -> {
                return result.tokenSupply.lockedAccounts.map {
                    TokenTeamAccount(
                        isLocked = true,
                        address = it.address,
                        shorthenAddress = shortenErd(it.address),
                        name = it.name,
                        balance =
                            when (token) {
                                is TokenDetailedRepositoryResponseModel.Success -> {
                                    balanceWithDecimals(balance = it.balance, decimals = token.tokenDetailed.decimals)
                                }
                                is TokenDetailedRepositoryResponseModel.GenericFailure -> {
                                    TODO()
                                }
                            },
                        pngUrl = token.tokenDetailed.assets!!.pngUrl
                    )
                }
            }
            is TokenSupplyRepositoryResponseModel.GenericFailure -> {
                TODO()
            }
        }
    }

}