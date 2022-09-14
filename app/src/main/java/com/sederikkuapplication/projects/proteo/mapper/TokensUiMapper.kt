package com.sederikkuapplication.projects.proteo.mapper

import com.sederikkuapplication.network.elrond.TokenWithBalance
import com.sederikkuapplication.projects.proteo.usecase.model.tokenlist.ProteoListTokensUseCaseModel
import javax.inject.Inject

class TokensUiMapper @Inject constructor() {

    fun mapUseCaseToUi(useCaseModel: ProteoListTokensUseCaseModel): List<TokenWithBalance> {
        return when (useCaseModel) {
            is ProteoListTokensUseCaseModel.Success -> {
                useCaseModel.tokens.map {
                    it
                }
            }
            is ProteoListTokensUseCaseModel.GenericFailure ->
                TODO("Seb: TransactionsUiMapperGenericError ${useCaseModel.cause}")
        }
    }
}
