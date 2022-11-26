package com.sederikkuapplication.shared.data.network

import com.example.common.network.safeApiCall
import javax.inject.Inject

class ElrondApiImpl @Inject constructor(
    private val elrondNetwork: ElrondNetwork
) : ElrondApi {

    override suspend fun getAccountDetails(address: String) = safeApiCall {
        elrondNetwork.getAccountDetails(address)
    }

    override suspend fun getAccountTokens(
        address: String,
        from: Number?,
        size: Number?,
        search: String?,
        name: String?,
        identifier: String?
    ) = safeApiCall {
        elrondNetwork.getAccountTokens(
            address = address,
            from = from,
            size = size,
            search = search,
            name = name,
            identifier = identifier
        )
    }

    override suspend fun getNetworkEconomics() = safeApiCall {
        elrondNetwork.getNetworkEconomics()
    }

    override suspend fun getNetworkStatistics() = safeApiCall {
        elrondNetwork.getNetworkStatistics()
    }

    override suspend fun getTokens() = safeApiCall {
        elrondNetwork.getTokens()
    }

    override suspend fun getToken(
        identifier: String
    ) = safeApiCall {
        elrondNetwork.getToken(
            identifier = identifier
        )
    }

    override suspend fun getTokenSupply(
        identifier: String
    ) = safeApiCall {
        elrondNetwork.getTokenSupply(
            identifier = identifier
        )
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
    ) = safeApiCall {
        elrondNetwork.getTokenTransfers(
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
    }

}