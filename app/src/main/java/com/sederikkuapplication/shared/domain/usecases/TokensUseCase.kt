package com.sederikkuapplication.shared.domain.usecases

import android.util.Log
import com.sederikkuapplication.features.home.domain.models.Token
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.domain.repository.TokensRepository
import javax.inject.Inject

class TokensUseCase @Inject constructor(
    private val repository: TokensRepository
) {
    suspend operator fun invoke() : List<Token> {
        return when (val result = repository.getTokens()) {
            is ListTokenDetailedRepositoryResponseModel.Success -> {
                result.tokens.map {
                    Token(
                        identifier = it.identifier,
                        name = it.name,
                        ticker = it.ticker,
                        pngUrl = it.assets?.pngUrl
                    )
                }
            }
            is ListTokenDetailedRepositoryResponseModel.GenericFailure -> {
                TODO()
            }
        }

    }
}