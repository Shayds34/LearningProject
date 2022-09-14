package com.sederikkuapplication.projects.proteo.data.repository.mapper

import com.example.common.network.GenericFailureCauseRepositoryModel
import com.example.common.network.NetworkResponse
import com.example.common.network.Response
import com.sederikkuapplication.projects.proteo.data.api.model.*
import com.sederikkuapplication.projects.proteo.data.repository.model.accountdetailed.AccountDetailedRepositoryModel
import com.sederikkuapplication.projects.proteo.data.repository.model.accountdetailed.ProteoAccountDetailedRepositoryResponseModel
import com.sederikkuapplication.projects.proteo.data.repository.model.accounttokens.ProteoListTokensRepositoryResponseModel
import com.sederikkuapplication.projects.proteo.data.repository.model.accounttokens.TokensRepositoryModel
import com.sederikkuapplication.projects.proteo.data.repository.model.stats.ProteoStatsRepositoryResponseModel
import com.sederikkuapplication.projects.proteo.data.repository.model.stats.StatsRepositoryModel
import com.sederikkuapplication.projects.proteo.data.repository.model.tokendetailed.ProteoTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.projects.proteo.data.repository.model.tokendetailed.TokenDetailedRepositoryModel
import com.sederikkuapplication.projects.proteo.data.repository.model.transactionlist.ProteoTransactionsRepositoryResponseModel
import com.sederikkuapplication.projects.proteo.data.repository.model.transactionlist.TransactionRepositoryModel
import com.sederikkuapplication.projects.proteo.data.repository.model.transferlist.ProteoTransfersRepositoryResponseModel
import com.sederikkuapplication.projects.proteo.data.repository.model.transferlist.TransfersRepositoryModel
import javax.inject.Inject

class ProteoRepositoryMapper @Inject constructor() {

    /**
     * Map AccountDetailed API to AccountDetailed Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<ProteoAccountsDetailedApiResponseModel>
    ): ProteoAccountDetailedRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                ProteoAccountDetailedRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: ProteoAccountsDetailedApiResponseModel
    ): ProteoAccountDetailedRepositoryResponseModel {
        return ProteoAccountDetailedRepositoryResponseModel.Success(
            accountDetailed = AccountDetailedRepositoryModel(
                address = apiModel.address,
                balance = apiModel.balance,
                nonce = apiModel.nonce,
                shard = apiModel.shard,
                username = apiModel.username
            )
        )
    }

    /**
     * Map Stats API to Stats Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<ProteoStatsApiResponseModel>
    ): ProteoStatsRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                ProteoStatsRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: ProteoStatsApiResponseModel
    ): ProteoStatsRepositoryResponseModel {
        return ProteoStatsRepositoryResponseModel.Success(
            stats = StatsRepositoryModel(
                accounts = apiModel.accounts,
                blocks = apiModel.blocks,
                epoch = apiModel.epoch,
                refreshRate = apiModel.refreshRate,
                roundsPassed = apiModel.roundsPassed,
                roundsPerEpoch = apiModel.roundsPerEpoch,
                shards = apiModel.shards,
                transactions = apiModel.transactions
            )
        )
    }

    /**
     * Map List Transactions API to List Transactions Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<List<ProteoTransactionApiResponseModel>>
    ): ProteoTransactionsRepositoryResponseModel {
        return when (response) {
            is Response.Success -> ProteoTransactionsRepositoryResponseModel.Success(
                transactions = mapToTransactionSuccess(response.successValue)
            )
            is Response.Failure -> {
                ProteoTransactionsRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToTransactionSuccess(
        apiModel: List<ProteoTransactionApiResponseModel>
    ): List<TransactionRepositoryModel> {
        return apiModel.map {
            TransactionRepositoryModel(
                sender = it.sender,
                timestamp = it.timestamp,
                value = it.value,
                function = it.function,
                action = it.action,
                operations = it.operations
            )
        }
    }

    /**
     * Map List Tokens API to List Tokens Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<List<ProteoTokensApiResponseModel>>
    ): ProteoListTokensRepositoryResponseModel {
        return when (response) {
            is Response.Success -> ProteoListTokensRepositoryResponseModel.Success(
                tokens = mapToSuccess(response.successValue)
            )
            is Response.Failure -> {
                ProteoListTokensRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        successValue: List<ProteoTokensApiResponseModel>
    ): List<TokensRepositoryModel> {
        return successValue.map {
            TokensRepositoryModel(
                identifier = it.identifier,
                name = it.name,
                ticker = it.ticker,
                decimals = it.decimals,
                price = it.price,
                marketcap = it.marketcap,
                supply = it.supply,
                circulatingSupply = it.circulatingSupply,
                balance = it.balance,
                valueUsd = it.valueUsd
            )
        }
    }

    /**
     * Map List Transfers (transactions) API to List Transfers Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<List<ProteoTransferApiResponseModel>>
    ): ProteoTransfersRepositoryResponseModel {
        return when (response) {
            is Response.Success -> ProteoTransfersRepositoryResponseModel.Success(
                transfers = mapToTransferSuccess(response.successValue)
            )
            is Response.Failure -> {
                ProteoTransfersRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToTransferSuccess(
        successValue: List<ProteoTransferApiResponseModel>
    ): List<TransfersRepositoryModel> {
        return successValue.map {
            TransfersRepositoryModel(
                sender = it.sender,
                timestamp = it.timestamp,
                value = it.value,
                function = it.function,
                action = it.action,
                operations = it.operations
            )
        }
    }

    /**
     * Map TokenDetailed API to TokenDetailed Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<ProteoTokenDetailedApiResponseModel>
    ): ProteoTokenDetailedRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                ProteoTokenDetailedRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: ProteoTokenDetailedApiResponseModel
    ): ProteoTokenDetailedRepositoryResponseModel {
        return ProteoTokenDetailedRepositoryResponseModel.Success(
            tokenDetailed = TokenDetailedRepositoryModel(
                identifier = apiModel.identifier,
                name = apiModel.name,
                ticker = apiModel.ticker,
                owner = apiModel.owner,
                minted = apiModel.minted,
                burnt = apiModel.burnt,
                initialMinted = apiModel.initialMinted,
                decimals = apiModel.decimals,
                isPaused = apiModel.isPaused,
                assets = apiModel.assets,
                transactions = apiModel.transactions,
                accounts = apiModel.accounts,
                canUpgrade = apiModel.canUpgrade,
                canMint = apiModel.canMint,
                canBurn = apiModel.canBurn,
                canChangeOwner = apiModel.canChangeOwner,
                canPause = apiModel.canPause,
                canFreeze = apiModel.canFreeze,
                canWipe = apiModel.canWipe,
                price = apiModel.price,
                marketCap = apiModel.marketCap,
                supply = apiModel.supply,
                circulatingSupply = apiModel.circulatingSupply,
                roles = apiModel.roles
            )
        )
    }
}