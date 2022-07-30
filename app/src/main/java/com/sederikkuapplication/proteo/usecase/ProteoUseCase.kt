package com.sederikkuapplication.proteo.usecase

import com.sederikkuapplication.proteo.usecase.model.accountdetailed.ProteoAccountDetailedUseCaseModel
import com.sederikkuapplication.proteo.usecase.model.stats.ProteoStatsUseCaseModel
import com.sederikkuapplication.proteo.usecase.model.tokendetailed.ProteoTokenDetailedUseCaseModel
import com.sederikkuapplication.proteo.usecase.model.tokenlist.ProteoListTokensUseCaseModel
import com.sederikkuapplication.proteo.usecase.model.transactionlist.ProteoListTransactionsUseCaseModel
import com.sederikkuapplication.proteo.usecase.model.transferlist.ProteoListTransfersUseCaseModel

interface ProteoUseCase {

    suspend fun getNetworkStats(): ProteoStatsUseCaseModel
    suspend fun getAccountDetails(address: String): ProteoAccountDetailedUseCaseModel
    suspend fun getAccountTransactions(
        address: String,
        from: String,
        size: String,
        receiver: String,
        status: String,
        order: String?,
        withOperations: String
    ): ProteoListTransactionsUseCaseModel

    suspend fun getAccountTokens(
        address: String,
        size: String
    ): ProteoListTokensUseCaseModel

    suspend fun getAccountTransfers(
        address: String,
        from: String,
        size: String,
        receiverOne: String,
        receiverTwo: String,
        status: String,
        order: String?
    ): ProteoListTransfersUseCaseModel

    suspend fun getTokenDetailed(token: String): ProteoTokenDetailedUseCaseModel

}
