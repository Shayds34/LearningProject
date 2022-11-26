package com.sederikkuapplication.shared.data.network

import com.example.common.network.NetworkResponse
import com.sederikkuapplication.shared.data.network.models.Transaction
import com.sederikkuapplication.shared.data.network.responsemodels.*

interface ElrondApi {

    suspend fun getAccountDetails(
        address: String
    ) : NetworkResponse<AccountDetailedApiResponseModel>

    suspend fun getAccountTokens(
        address: String,
        from: Number?,
        size: Number?,
        search: String?,
        name: String?,
        identifier: String?
    ) : NetworkResponse<List<TokenDetailedApiResponseModel>>

    suspend fun getNetworkEconomics() : NetworkResponse<EconomicsApiResponseModel>

    suspend fun getNetworkStatistics() : NetworkResponse<StatsApiResponseModel>

    suspend fun getTokens() : NetworkResponse<List<TokenDetailedApiResponseModel>>

    suspend fun getToken(
        identifier: String
    ): NetworkResponse<TokenDetailedApiResponseModel>

    suspend fun getTokenSupply(
        identifier: String
    ) : NetworkResponse<TokenSupplyApiResponseModel>

    suspend fun getTokenTransfers(
        identifier: String,
        from: Number?,
        size: Number?,
        sender: String?,
        receiver: String?,
        senderShard: Number?,
        receiverShard: Number?,
        miniBlockHash: String?,
        hashes: String?,
        status: String?,
        search: String?,
        before: Number?,
        after: Number?,
        order: String?
    ) : NetworkResponse<List<TransactionApiResponseModel>>

}