package com.sederikkuapplication.projects.proteo.data.repository

import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.projects.proteo.data.api.ProteoApi
import com.sederikkuapplication.projects.proteo.data.repository.mapper.ProteoRepositoryMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ProteoRepositoryImpl @Inject constructor(
    private val api: ProteoApi,
    private val mapper: ProteoRepositoryMapper,
    @Named(DispatchersName.DATA) private val dispatcher: CoroutineDispatcher
) : ProteoRepository {

    /**
     * Returns an AccountsDetailed object
     */
    override suspend fun getAccountDetails(address: String) =
        withContext(dispatcher) {
            val apiResponse = api.getAccountDetails(address)
            mapper.mapApiToRepository(apiResponse)
        }

    /**
     * Returns a Stats object
     */
    override suspend fun getNetworkStats() =
        withContext(dispatcher) {
            val apiResponse = api.getNetworkStats()
            mapper.mapApiToRepository(apiResponse)
        }

    /**
     * Returns a list of Transactions for an Account
     */
    override suspend fun getAccountTransactions(
        address: String,
        from: String,
        size: String,
        receiver: String,
        status: String,
        order: String?,
        withOperations: String
    ) =
        withContext(dispatcher) {
            val apiResponse = api.getAutostakingTransactions(
                address = address,
                from = from,
                size = size,
                receiver = receiver,
                status = status,
                order = order,
                withOperations = withOperations
            )
            mapper.mapApiToRepository(apiResponse)
        }

    /**
     * Returns a list of TokensWithBalance for an Account
     */
    override suspend fun getAccountTokens(
        address: String,
        size: String
    ) =
        withContext(dispatcher) {
            val apiResponse = api.getAccountTokens(
                address = address,
                size = size,
            )
            mapper.mapApiToRepository(apiResponse)
        }

    /**
     * Returns a list of Transaction (Transfers) between two Account
     *
     * [receiverOne] as Account number one
     *
     * [receiverTwo] as Account number two
     */
    override suspend fun getAccountTransfers(
        address: String,
        from: String,
        size: String,
        receiverOne: String,
        receiverTwo: String,
        status: String,
        order: String?
    ) =
        withContext(dispatcher) {
            val apiResponse = api.getAccountTransfers(
                address = address,
                from = from,
                size = size,
                receiverOne = receiverOne,
                receiverTwo = receiverTwo,
                status = status,
                order = order,
            )
            mapper.mapApiToRepository(apiResponse)
        }

    /**
     * Returns an TokenDetailed object
     */
    override suspend fun getTokenDetailed(token: String) =
        withContext(dispatcher) {
            val apiResponse = api.getTokenDetailed(token)
            mapper.mapApiToRepository(apiResponse)
        }
}