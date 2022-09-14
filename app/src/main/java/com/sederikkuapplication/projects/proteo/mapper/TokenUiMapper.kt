package com.sederikkuapplication.projects.proteo.mapper

import com.sederikkuapplication.network.elrond.TokenDetailed
import com.sederikkuapplication.projects.proteo.usecase.model.tokendetailed.ProteoTokenDetailedUseCaseModel
import javax.inject.Inject

class TokenUiMapper @Inject constructor() {

    fun mapUseCaseToUi(useCaseModel: ProteoTokenDetailedUseCaseModel): TokenDetailed {
        return when (useCaseModel) {
            is ProteoTokenDetailedUseCaseModel.Success -> {
                TokenDetailed(
                    identifier = useCaseModel.tokenDetailed.identifier,
                    name = useCaseModel.tokenDetailed.name,
                    ticker = useCaseModel.tokenDetailed.ticker,
                    owner = useCaseModel.tokenDetailed.owner,
                    minted = useCaseModel.tokenDetailed.minted,
                    burnt = useCaseModel.tokenDetailed.burnt,
                    initialMinted = useCaseModel.tokenDetailed.initialMinted,
                    decimals = useCaseModel.tokenDetailed.decimals,
                    isPaused = useCaseModel.tokenDetailed.isPaused,
                    assets = useCaseModel.tokenDetailed.assets,
                    transactions = useCaseModel.tokenDetailed.transactions,
                    accounts = useCaseModel.tokenDetailed.accounts,
                    canUpgrade = useCaseModel.tokenDetailed.canUpgrade,
                    canMint = useCaseModel.tokenDetailed.canMint,
                    canBurn = useCaseModel.tokenDetailed.canBurn,
                    canChangeOwner = useCaseModel.tokenDetailed.canChangeOwner,
                    canPause = useCaseModel.tokenDetailed.canPause,
                    canFreeze = useCaseModel.tokenDetailed.canFreeze,
                    canWipe = useCaseModel.tokenDetailed.canWipe,
                    price = useCaseModel.tokenDetailed.price,
                    marketCap = useCaseModel.tokenDetailed.marketCap,
                    supply = useCaseModel.tokenDetailed.supply,
                    circulatingSupply = useCaseModel.tokenDetailed.circulatingSupply,
                    roles = useCaseModel.tokenDetailed.roles
                )
            }
            is ProteoTokenDetailedUseCaseModel.GenericFailure ->
                TODO("Seb: TransactionsUiMapperGenericError ${useCaseModel.cause}")
        }
    }
}
