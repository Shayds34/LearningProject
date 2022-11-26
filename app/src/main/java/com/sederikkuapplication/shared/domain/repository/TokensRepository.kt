package com.sederikkuapplication.shared.domain.repository

import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokensupply.TokenSupplyRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.transaction.ListTransactionRepositoryResponseModel

interface TokensRepository {

    /**
     * TOKENS
     * > Returns all tokens available on the blockchain
     */
    suspend fun getTokens(): ListTokenDetailedRepositoryResponseModel

    /**
     * TOKEN
     *  > Returns token details based on a specific token identifier
     *
     */
    suspend fun getToken(identifier: String): TokenDetailedRepositoryResponseModel


    /**
     * TOKEN SUPPLY
     * > Returns general supply information for a specific token
     */
    suspend fun getTokenSupply(identifier: String): TokenSupplyRepositoryResponseModel

    /**
     * TOKEN TRANSFERS
     * > Returns both transfers triggerred by a user account (type = Transaction),
     * as well as transfers triggerred by smart contracts (type = SmartContractResult),
     * thus providing a full picture of all in/out value transfers for a given account
     */
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
    ): ListTransactionRepositoryResponseModel

}