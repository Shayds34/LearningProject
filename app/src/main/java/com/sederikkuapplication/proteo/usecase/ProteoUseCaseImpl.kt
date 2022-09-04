package com.sederikkuapplication.proteo.usecase

import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.proteo.data.repository.ProteoRepository
import com.sederikkuapplication.proteo.usecase.mapper.ProteoUseCaseMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ProteoUseCaseImpl @Inject constructor(
    private val repository: ProteoRepository,
    private val mapper: ProteoUseCaseMapper,
    @Named(DispatchersName.DOMAIN) private val dispatcher: CoroutineDispatcher
) : ProteoUseCase {

    override suspend fun getAccountDetails(
        address: String
    ) = withContext(dispatcher) {
        mapper.mapRepositoryToUseCase(
            repository.getAccountDetails(address)
        )
    }

    override suspend fun getNetworkStats() = withContext(dispatcher) {
        mapper.mapRepositoryToUseCase(
            repository.getNetworkStats()
        )
    }

    override suspend fun getAccountTransactions(
        address: String,
        from: String,
        size: String,
        receiver: String,
        status: String,
        order: String?,
        withOperations: String
    ) = withContext(dispatcher) {
        mapper.mapRepositoryToUseCase(
            repository.getAccountTransactions(
                address = address,
                from = from,
                size = size,
                receiver = receiver,
                status = status,
                order = order,
                withOperations = withOperations
            )
        )
    }

    override suspend fun getAccountTokens(
        address: String,
        size: String
    ) = withContext(dispatcher) {
        mapper.mapRepositoryToUseCase(
            repository.getAccountTokens(
                address = address,
                size = size
            )
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
    ) = withContext(dispatcher) {
        mapper.mapRepositoryToUseCase(
            repository.getAccountTransfers(
                address = address,
                from = from,
                size = size,
                receiverOne = receiverOne,
                receiverTwo = receiverTwo,
                status = status,
                order = order
            )
        )
    }

    override suspend fun getTokenDetailed(
        token: String
    ) = withContext(dispatcher) {
        mapper.mapRepositoryToUseCase(
            repository.getTokenDetailed(token)
        )
    }

}
