package com.sederikkuapplication.shared.data.network

import com.sederikkuapplication.shared.data.network.responsemodels.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ElrondNetwork {

    /**
     * ACCOUNT DETAILS
     *
     * Returns account details for a given [address].
     */
    @GET("accounts/{address}")
    suspend fun getAccountDetails(
        @Path("address") address: String
    ) : AccountDetailedApiResponseModel

    /**
     * ACCOUNT TOKENS
     *
     * Returns a list of all available fungible tokens for a given [address], together with their balance.
     *
     * Optionals queries :
     * - [from] : Number of items to skip for the result set
     * - [size] : Number of items to retrieve
     * - [search] : Search by collection identifier
     * - [name] : Search by token name
     * - [identifier] : Search by token identifier
     * - Multiple-identifiers : A comma-separated list of identifiers to filter by
     */
    @GET("accounts/{address}/tokens")
    suspend fun getAccountTokens(
        @Path("address") address: String,
        @Query("from") from: Number?,
        @Query("size") size: Number?,
        @Query("search") search: String?,
        @Query("name") name: String?,
        @Query("identifier") identifier: String?,
        // @Query("identifiers") identifiers: List<String>?,
    ) : List<TokenDetailedApiResponseModel>

    /**
     * ACCOUNT TRANSACTION LIST
     *
     * Returns details of all transactions where the account [address] is sender or receiver.
     *
     * Optionals queries :
     *
     * - [from] : Number of items to skip for the result set
     * - [size] : Number of items to retrieve
     * - [sender] : Address of the transaction sender
     * - [receiver] : Search by multiple receiver addresses, comma-separated
     * - [token] : Identifier of the token
     * - [senderShard] : Id of the shard the sender address belongs to
     * - [miniBlockHash] : Filter by miniblock hash
     * - [hashes] : Filter by a comma-separated list of transaction hashes
     * - [status] : Status of the transaction (success / pending / invalid / fail)
     * - [search] : Search in data object
     * - [before] : Before timestamp
     * - [after] : After timestamp
     * - [order] : Sort order (asc/desc)
     * - [withScResults] : Return scResults for transactions. When "withScresults" parameter is applied, complexity estimation is 200
     * - [withOperations] : Return operations for transactions. When "withOperations" parameter is applied, complexity estimation is 200
     * - [withLogs] : Return logs for transactions. When "withLogs" parameter is applied, complexity estimation is 200
     * - [withScamInfo] : Scam Informations
     * - [computeScamInfo] : ...
     */
    @GET("accounts/{address}/transactions")
    suspend fun getAccountTransactionList(
        @Path("address") address: String,
        @Query("from") from: Number?,
        @Query("size") size: Number?,
        @Query("sender") sender: String?,
        @Query("receiver") receiver: String?,
        @Query("token") token: String?,
        @Query("senderShard") senderShard: Number?,
        @Query("miniBlockHash") miniBlockHash: String?,
        @Query("hashes") hashes: String?,
        @Query("status") status: String?,
        @Query("search") search: String?,
        @Query("before") before: Number?,
        @Query("after") after: Number?,
        @Query("order") order: String?,
        @Query("withScResults") withScResults: Boolean?,
        @Query("withOperations") withOperations: Boolean?,
        @Query("withLogs") withLogs: Boolean?,
        @Query("withScamInfo") withScamInfo: Boolean?,
        @Query("computeScamInfo") computeScamInfo: Boolean?
    ) : List<TransactionApiResponseModel>

    /**
     * NETWORK ECONOMICS
     *
     * Returns general economics information
     */
    @GET("economics")
    suspend fun getNetworkEconomics() : EconomicsApiResponseModel

    /**
     * NETWORK STATISTICS
     *
     * Returns general network statistics
     */
    @GET("stats")
    suspend fun getNetworkStatistics() : StatsApiResponseModel


    /**
     * TOKENS
     *
     * Returns all tokens available on the blockchain
     */
    @GET("tokens?size=139")
    suspend fun getTokens() : List<TokenDetailedApiResponseModel>

    /**
     * TOKEN
     *
    * Returns token details based on a specific token [identifier]
    */
    @GET("tokens/{identifier}")
    suspend fun getToken(
        @Path("identifier") identifier: String
    ) : TokenDetailedApiResponseModel

    /**
     * TOKEN SUPPLY
     *
     * Returns general supply information for a specific token, with [identifier]
     *
     * With denominated (optional @Query) = false
     */
    @GET("tokens/{identifier}/supply")
    suspend fun getTokenSupply(
        @Path("identifier") identifier: String,
    ) : TokenSupplyApiResponseModel

    /**
     * TOKEN TRANSFERS
     *
     * Returns both transfers triggerred by a user account (type = Transaction),
     * as well as transfers triggerred by smart contracts (type = SmartContractResult),
     * thus providing a full picture of all in/out value transfers for a "token [identifier]"
     *
     * Optionals quueries :
     * - [from] : Number of items to skip for the result set
     * - [size] : Number of items to retrieve
     * - [sender] : Address of the transfer sender
     * - [receiver] : Search by multiple receiver addresses, comma-separated
     * - [senderShard] : Id of the shard the sender address belongs to
     * - [receiverShard] : Id of the shard the receiver address belongs to
     * - [miniBlockHash] : Filter by miniblock hash
     * - [hashes] : Filter by a comma-separated list of transfer hashes
     * - [status] : Status of the transaction (success / pending / invalid / fail)
     * - [search] : Search in data object
     * - [before] : Before timestamp
     * - [after] : After timestamp
     * - [order] : Sort order (asc/desc)
     */
    @GET("tokens/{identifier}/transfers")
    suspend fun getTokenTransfers(
        @Path("identifier") identifier: String,
        @Query("from") from: Number?,
        @Query("size") size: Number?,
        @Query("sender") sender: String?,
        @Query("receiver") receiver: String?,
        @Query("senderShard") senderShard: Number?,
        @Query("receiverShard") receiverShard: Number?,
        @Query("miniBlockHash") miniBlockHash: String?,
        @Query("hashes") hashes: String?,
        @Query("status") status: String?,
        @Query("search") search: String?,
        @Query("before") before: Number?,
        @Query("after") after: Number?,
        @Query("order") order: String?,
    ) : List<TransactionApiResponseModel>

}