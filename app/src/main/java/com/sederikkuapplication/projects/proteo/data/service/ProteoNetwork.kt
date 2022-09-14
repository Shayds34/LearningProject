package com.sederikkuapplication.projects.proteo.data.service

import com.sederikkuapplication.network.network.Economics
import com.sederikkuapplication.projects.proteo.data.api.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProteoNetwork {

    // Elrond Api: Network tag
    @GET("economics")
    suspend fun getNetworkEconomics(): Economics

    @GET("stats")
    suspend fun getNetworkStats(): ProteoStatsApiResponseModel

    // Returns token details based on a specific token identifier
    @GET("tokens/{identifier}")
    suspend fun getTokenDetailed(
        @Path("identifier") identifier: String
    ): ProteoTokenDetailedApiResponseModel

    @GET("accounts/{address}")
    suspend fun getAccountDetails(
        @Path("address") address: String?
    ): ProteoAccountsDetailedApiResponseModel

    @GET("accounts/{address}/tokens")
    suspend fun getAccountTokens(
        @Path("address") address: String,
        @Query("size") size: String
    ): List<ProteoTokensApiResponseModel>

    @GET("accounts/{address}/transactions")
    suspend fun getAccountTransactions(
        @Path("address") address: String,
        @Query("from") from: String,
        @Query("size") size: String,
        @Query("receiver") receiver: String,
        @Query("status") status: String,
        @Query("order") order: String?,
        @Query("withOperations") withOperations: String
    ): List<ProteoTransactionApiResponseModel>

    @GET("accounts/{address}/transfers")
    /* Returns both transfers triggerred by a user account (type = Transaction),
    * as well as transfers triggerred by smart contracts (type = SmartContractResult),
    * thus providing a full picture of all in/out value transfers for a given account
    * */
    suspend fun getAccountTransfers(
        @Path("address") address: String,
        @Query("from") from: String?,
        @Query("size") size: String?,
        @Query("receiver") receiverOne: String?,
        @Query("receiver") receiverTwo: String?,
        @Query("status") status: String?,
        @Query("order") order: String?
    ): List<ProteoTransferApiResponseModel>
}
