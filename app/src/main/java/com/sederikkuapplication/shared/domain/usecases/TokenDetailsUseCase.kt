package com.sederikkuapplication.shared.domain.usecases

import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.domain.models.TokenDetailed
import com.sederikkuapplication.shared.domain.repository.TokensRepository
import com.sederikkuapplication.shared.utils.balanceWithDecimals
import com.sederikkuapplication.shared.utils.supplyFormat
import javax.inject.Inject

class TokenDetailsUseCase @Inject constructor(
    private val repository: TokensRepository
) {
    suspend operator fun invoke(
        identifier: String
    ): TokenDetailed {
        when (val result = repository.getToken(identifier)) {
            is TokenDetailedRepositoryResponseModel.Success -> {
                return TokenDetailed(
                    identifier = result.tokenDetailed.identifier,
                    name = result.tokenDetailed.name,
                    ticker = result.tokenDetailed.ticker,
                    owner = result.tokenDetailed.owner,
                    minted = result.tokenDetailed.minted,
                    burnt = result.tokenDetailed.burnt?.let {
                        balanceWithDecimals(it, result.tokenDetailed.decimals)
                    },
                    initialMinted = result.tokenDetailed.initialMinted?.let { supplyFormat(it) },
                    decimals = result.tokenDetailed.decimals,
                    isPaused = result.tokenDetailed.isPaused,
                    assets = result.tokenDetailed.assets,
                    transactions = result.tokenDetailed.transactions,
                    accounts = result.tokenDetailed.accounts,
                    canUpgrade = result.tokenDetailed.canUpgrade,
                    canMint = result.tokenDetailed.canMint,
                    canBurn = result.tokenDetailed.canBurn,
                    canChangeOwner = result.tokenDetailed.canChangeOwner,
                    canPause = result.tokenDetailed.canPause,
                    canFreeze = result.tokenDetailed.canFreeze,
                    canWipe = result.tokenDetailed.canWipe,
                    price = result.tokenDetailed.price,
                    marketCap = result.tokenDetailed.marketCap,
                    supply = supplyFormat(result.tokenDetailed.supply),
                    circulatingSupply = supplyFormat(result.tokenDetailed.circulatingSupply),
                    role = result.tokenDetailed.role
                )
            }
            is TokenDetailedRepositoryResponseModel.GenericFailure -> {
                TODO()
            }
        }
    }
}