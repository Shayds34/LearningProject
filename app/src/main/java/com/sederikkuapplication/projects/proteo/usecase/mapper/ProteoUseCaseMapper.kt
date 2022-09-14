package com.sederikkuapplication.projects.proteo.usecase.mapper

import com.example.common.network.GenericFailureCauseUseCaseModel
import com.sederikkuapplication.network.elrond.TokenWithBalance
import com.sederikkuapplication.network.elrond.transaction.Transaction
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
import com.sederikkuapplication.projects.proteo.usecase.model.accountdetailed.AccountDetailedUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.accountdetailed.ProteoAccountDetailedUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.stats.ProteoStatsUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.stats.StatsUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.tokendetailed.ProteoTokenDetailedUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.tokendetailed.TokenDetailedUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.tokenlist.ProteoListTokensUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.transactionlist.ProteoListTransactionsUseCaseModel
import com.sederikkuapplication.projects.proteo.usecase.model.transferlist.ProteoListTransfersUseCaseModel
import javax.inject.Inject

class ProteoUseCaseMapper @Inject constructor() {

    /**
     * Map Account Detailed Repository to Account Detailed UseCase
     */
    fun mapRepositoryToUseCase(repoModel: ProteoAccountDetailedRepositoryResponseModel) =
        when (repoModel) {
            is ProteoAccountDetailedRepositoryResponseModel.Success -> {
                ProteoAccountDetailedUseCaseModel.Success(
                    accountDetailed = mapTokenDetailed(repoModel.accountDetailed)
                )
            }
            is ProteoAccountDetailedRepositoryResponseModel.GenericFailure -> {
                ProteoAccountDetailedUseCaseModel.GenericFailure(
                    cause = GenericFailureCauseUseCaseModel.UNKNOWN(
                        Throwable(
                            repoModel.cause.toString()
                        )
                    )
                )
            }
        }

    private fun mapTokenDetailed(accountDetailed: AccountDetailedRepositoryModel) =
        AccountDetailedUseCaseModel(
            address = accountDetailed.address,
            balance = accountDetailed.balance,
            nonce = accountDetailed.nonce,
            shard = accountDetailed.shard,
            username = accountDetailed.username
        )

    /**
     * Map Stats Repository to Stats UseCase
     */
    fun mapRepositoryToUseCase(repoModel: ProteoStatsRepositoryResponseModel) =
        when (repoModel) {
            is ProteoStatsRepositoryResponseModel.Success -> {
                ProteoStatsUseCaseModel.Success(
                    stats = mapStats(repoModel.stats)
                )
            }
            is ProteoStatsRepositoryResponseModel.GenericFailure -> {
                ProteoStatsUseCaseModel.GenericFailure(
                    cause = GenericFailureCauseUseCaseModel.UNKNOWN(
                        Throwable(
                            repoModel.cause.toString()
                        )
                    )
                )
            }
        }

    private fun mapStats(stats: StatsRepositoryModel) =
        StatsUseCaseModel(
            accounts = stats.accounts,
            blocks = stats.blocks,
            epoch = stats.epoch,
            refreshRate = stats.refreshRate,
            roundsPassed = stats.roundsPassed,
            roundsPerEpoch = stats.roundsPerEpoch,
            shards = stats.shards,
            transactions = stats.transactions
        )

    /**
     * Map List<Transaction> Repository to List<Transaction> UseCase
     */
    fun mapRepositoryToUseCase(repoModel: ProteoTransactionsRepositoryResponseModel) =
        when (repoModel) {
            is ProteoTransactionsRepositoryResponseModel.Success -> {
                ProteoListTransactionsUseCaseModel.Success(
                    transactions = mapTransactionsList(repoModel.transactions)
                )
            }
            is ProteoTransactionsRepositoryResponseModel.GenericFailure -> {
                ProteoListTransactionsUseCaseModel.GenericFailure(
                    cause = GenericFailureCauseUseCaseModel.UNKNOWN(
                        Throwable(
                            repoModel.cause.toString()
                        )
                    )
                )
            }
        }

    private fun mapTransactionsList(transfers: List<TransactionRepositoryModel>): List<Transaction> {
        return transfers.map {
            Transaction(
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
     * Map Token Repository to Token UseCase
     */
    fun mapRepositoryToUseCase(repoModel: ProteoListTokensRepositoryResponseModel) =
        when (repoModel) {
            is ProteoListTokensRepositoryResponseModel.Success -> {
                ProteoListTokensUseCaseModel.Success(
                    tokens = mapTokensList(repoModel.tokens)
                )
            }
            is ProteoListTokensRepositoryResponseModel.GenericFailure -> {
                ProteoListTokensUseCaseModel.GenericFailure(
                    cause = GenericFailureCauseUseCaseModel.UNKNOWN(
                        Throwable(
                            repoModel.cause.toString()
                        )
                    )
                )
            }
        }

    private fun mapTokensList(repoModel: List<TokensRepositoryModel>): List<TokenWithBalance> {
        return repoModel.map {
            TokenWithBalance(
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
     * Map Transfers = List<Transaction> Repository to Transfers = List<Transaction> UseCase
     */
    fun mapRepositoryToUseCase(repoModel: ProteoTransfersRepositoryResponseModel) =
        when (repoModel) {
            is ProteoTransfersRepositoryResponseModel.Success -> {
                ProteoListTransfersUseCaseModel.Success(
                    transfers = mapTransfersList(repoModel.transfers)
                )
            }
            is ProteoTransfersRepositoryResponseModel.GenericFailure -> {
                ProteoListTransfersUseCaseModel.GenericFailure(
                    cause = GenericFailureCauseUseCaseModel.UNKNOWN(
                        Throwable(
                            repoModel.cause.toString()
                        )
                    )
                )
            }
        }

    private fun mapTransfersList(transfers: List<TransfersRepositoryModel>): List<Transaction> {
        return transfers.map {
            Transaction(
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
     * Map Token Detailed Repository to Token Detailed UseCase
     */
    fun mapRepositoryToUseCase(repoModel: ProteoTokenDetailedRepositoryResponseModel) =
        when (repoModel) {
            is ProteoTokenDetailedRepositoryResponseModel.Success -> {
                ProteoTokenDetailedUseCaseModel.Success(
                    tokenDetailed = mapTokenDetailed(repoModel.tokenDetailed)
                )
            }
            is ProteoTokenDetailedRepositoryResponseModel.GenericFailure -> {
                ProteoTokenDetailedUseCaseModel.GenericFailure(
                    cause = GenericFailureCauseUseCaseModel.UNKNOWN(
                        Throwable(
                            repoModel.cause.toString()
                        )
                    )
                )
            }
        }

    private fun mapTokenDetailed(tokenDetailed: TokenDetailedRepositoryModel) =
        TokenDetailedUseCaseModel(
            identifier = tokenDetailed.identifier,
            name = tokenDetailed.name,
            ticker = tokenDetailed.ticker,
            owner = tokenDetailed.owner,
            minted = tokenDetailed.minted,
            burnt = tokenDetailed.burnt,
            initialMinted = tokenDetailed.initialMinted,
            decimals = tokenDetailed.decimals,
            isPaused = tokenDetailed.isPaused,
            assets = tokenDetailed.assets,
            transactions = tokenDetailed.transactions,
            accounts = tokenDetailed.accounts,
            canUpgrade = tokenDetailed.canUpgrade,
            canMint = tokenDetailed.canMint,
            canBurn = tokenDetailed.canBurn,
            canChangeOwner = tokenDetailed.canChangeOwner,
            canPause = tokenDetailed.canPause,
            canFreeze = tokenDetailed.canFreeze,
            canWipe = tokenDetailed.canWipe,
            price = tokenDetailed.price,
            marketCap = tokenDetailed.marketCap,
            supply = tokenDetailed.supply,
            circulatingSupply = tokenDetailed.circulatingSupply,
            roles = tokenDetailed.roles
        )
}
