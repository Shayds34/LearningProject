package com.sederikkuapplication.shared.domain.repository

import com.sederikkuapplication.shared.data.repository.models.economics.EconomicsRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.stats.StatsRepositoryResponseModel

interface NetworkRepository {

    /**
     * NETWORK ECONOMICS
     * > Returns general economics information
     */
    suspend fun getNetworkEconomics(): EconomicsRepositoryResponseModel

    /**
     * NETWORK STATISTICS
     * > Returns general network statistics
     */
    suspend fun getNetworkStatistics(): StatsRepositoryResponseModel

}