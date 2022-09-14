package com.sederikkuapplication.projects.proteo.data.api

import com.example.common.network.NetworkResponse
import com.sederikkuapplication.projects.proteo.data.api.model.*

interface ProteoApi {
    suspend fun getNetworkStats(): NetworkResponse<ProteoStatsApiResponseModel>

    suspend fun getAccountDetails(
        address: String?
    ): NetworkResponse<ProteoAccountsDetailedApiResponseModel>

    suspend fun getAutostakingTransactions(
        address: String,
        from: String,
        size: String,
        receiver: String,
        status: String,
        order: String?,
        withOperations: String
    ): NetworkResponse<List<ProteoTransactionApiResponseModel>>

    suspend fun getAccountTokens(
        address: String,
        size: String
    ): NetworkResponse<List<ProteoTokensApiResponseModel>>

    suspend fun getAccountTransfers(
        address: String,
        from: String,
        size: String,
        receiverOne: String,
        receiverTwo: String,
        status: String,
        order: String?,
    ): NetworkResponse<List<ProteoTransferApiResponseModel>>

    suspend fun getTokenDetailed(
        token: String
    ): NetworkResponse<ProteoTokenDetailedApiResponseModel>
}