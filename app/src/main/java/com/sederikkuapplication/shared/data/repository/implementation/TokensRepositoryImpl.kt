package com.sederikkuapplication.shared.data.repository.implementation

import android.app.appsearch.AppSearchManager
import android.util.Log
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.shared.data.network.ElrondApi
import com.sederikkuapplication.shared.data.repository.mapper.TokensRepositoryMapper
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.TokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokensupply.TokenSupplyRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.transaction.ListTransactionRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.transaction.TransactionRepositoryModel
import com.sederikkuapplication.shared.domain.repository.TokensRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class TokensRepositoryImpl @Inject constructor(
    private val api: ElrondApi,
    private val mapper: TokensRepositoryMapper,
    @Named(DispatchersName.DATA) private val dispatcher: CoroutineDispatcher
) : TokensRepository {

    override suspend fun getTokens(): ListTokenDetailedRepositoryResponseModel =
        withContext(dispatcher) {
            val apiResponse = api.getTokens()
            mapper.mapApiToRepository(apiResponse)
        }

    override suspend fun getToken(identifier: String): TokenDetailedRepositoryResponseModel =
        withContext(dispatcher) {
            val apiResponse = api.getToken(identifier = identifier)
            mapper.mapApiToRepository(apiResponse)
    }

    override suspend fun getTokenSupply(identifier: String): TokenSupplyRepositoryResponseModel =
        withContext(dispatcher) {
            val apiResponse = api.getTokenSupply(identifier = identifier)
            mapper.mapApiToRepository(apiResponse)
    }

    override suspend fun getTokenTransfers(
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
    ) = withContext(dispatcher) {
            val apiResponse = api.getTokenTransfers(
                identifier = identifier,
                from = from,
                size = size,
                sender = sender,
                receiver = receiver,
                senderShard = senderShard,
                receiverShard = receiverShard,
                miniBlockHash = miniBlockHash,
                hashes = hashes,
                status = status,
                search = search,
                before = before,
                after = after,
                order = order
            )
            mapper.mapApiToRepository(apiResponse)
        }

}