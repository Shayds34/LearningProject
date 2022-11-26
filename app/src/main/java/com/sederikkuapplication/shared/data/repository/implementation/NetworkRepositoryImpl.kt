package com.sederikkuapplication.shared.data.repository.implementation

import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.shared.data.network.ElrondApi
import com.sederikkuapplication.shared.data.repository.mapper.NetworkRepositoryMapper
import com.sederikkuapplication.shared.data.repository.models.economics.EconomicsRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.stats.StatsRepositoryResponseModel
import com.sederikkuapplication.shared.domain.repository.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class NetworkRepositoryImpl @Inject constructor(
    private val api: ElrondApi,
    private val mapper: NetworkRepositoryMapper,
    @Named(DispatchersName.DATA) private val dispatcher: CoroutineDispatcher
) : NetworkRepository {

    override suspend fun getNetworkEconomics(): EconomicsRepositoryResponseModel =
        withContext(dispatcher) {
            val apiResponse = api.getNetworkEconomics()
            mapper.mapApiToRepository(apiResponse)
        }

    override suspend fun getNetworkStatistics(): StatsRepositoryResponseModel =
        withContext(dispatcher) {
            val apiResponse = api.getNetworkStatistics()
            mapper.mapApiToRepository(apiResponse)
    }

}