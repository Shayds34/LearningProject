package com.sederikkuapplication.proteo.data.api

import com.example.common.network.safeApiCall
import com.sederikkuapplication.proteo.data.service.ProteoNetwork
import javax.inject.Inject

class ProteoApiImpl @Inject constructor(
    private val proteoNetwork: ProteoNetwork
) : ProteoApi {

    override suspend fun getNetworkStats() = safeApiCall {
        proteoNetwork.getNetworkStats()
    }

    override suspend fun getAccountDetails(address: String?) = safeApiCall {
        proteoNetwork.getAccountDetails(address)
    }

    override suspend fun getAutostakingTransactions(
        address: String,
        from: String,
        size: String,
        receiver: String,
        status: String,
        order: String?,
        withOperations: String
    ) = safeApiCall {
        proteoNetwork.getAccountTransactions(
            address = address,
            from = from,
            size = size,
            receiver = receiver,
            status = status,
            order = order,
            withOperations = withOperations
        )
    }

    override suspend fun getAccountTokens(
        address: String,
        size: String
    ) = safeApiCall {
        proteoNetwork.getAccountTokens(
            address = address,
            size = size
        )
    }

    override suspend fun getAccountTransfers(
        address: String,
        from: String,
        size: String,
        receiverOne: String,
        receiverTwo: String,
        status: String,
        order: String?
    ) = safeApiCall {
        proteoNetwork.getAccountTransfers(
            address = address,
            from = from,
            size = size,
            receiverOne = receiverOne,
            receiverTwo = receiverTwo,
            status = status,
            order = order
        )
    }

    override suspend fun getTokenDetailed(
        token: String
    ) = safeApiCall {
        proteoNetwork.getTokenDetailed(
            identifier = token
        )
    }
}
