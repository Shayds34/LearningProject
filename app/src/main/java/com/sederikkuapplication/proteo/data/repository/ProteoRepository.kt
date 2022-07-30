package com.sederikkuapplication.proteo.data.repository

import com.sederikkuapplication.proteo.data.repository.model.accountdetailed.ProteoAccountDetailedRepositoryResponseModel
import com.sederikkuapplication.proteo.data.repository.model.accounttokens.ProteoListTokensRepositoryResponseModel
import com.sederikkuapplication.proteo.data.repository.model.stats.ProteoStatsRepositoryResponseModel
import com.sederikkuapplication.proteo.data.repository.model.tokendetailed.ProteoTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.proteo.data.repository.model.transactionlist.ProteoTransactionsRepositoryResponseModel
import com.sederikkuapplication.proteo.data.repository.model.transferlist.ProteoTransfersRepositoryResponseModel

interface ProteoRepository {

    suspend fun getNetworkStats(): ProteoStatsRepositoryResponseModel
    suspend fun getAccountDetails(address: String): ProteoAccountDetailedRepositoryResponseModel
    suspend fun getAccountTransactions(
        address: String,
        from: String,
        size: String,
        receiver: String,
        status: String,
        order: String?,
        withOperations: String
    ): ProteoTransactionsRepositoryResponseModel

    suspend fun getAccountTokens(
        address: String,
        size: String
    ): ProteoListTokensRepositoryResponseModel

    suspend fun getAccountTransfers(
        address: String,
        from: String,
        size: String,
        receiverOne: String,
        receiverTwo: String,
        status: String,
        order: String?
    ): ProteoTransfersRepositoryResponseModel

    suspend fun getTokenDetailed(
        token: String,
    ): ProteoTokenDetailedRepositoryResponseModel
}