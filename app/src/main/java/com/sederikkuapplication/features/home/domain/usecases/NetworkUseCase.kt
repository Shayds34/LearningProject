package com.sederikkuapplication.features.home.domain.usecases

import com.sederikkuapplication.features.home.domain.models.Stats
import com.sederikkuapplication.shared.data.repository.models.economics.EconomicsRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.stats.StatsRepositoryResponseModel
import com.sederikkuapplication.shared.domain.repository.NetworkRepository
import javax.inject.Inject

class NetworkUseCase @Inject constructor(
    private val repository: NetworkRepository
) {

    suspend operator fun invoke(): Stats {
        val statistics = repository.getNetworkStatistics()
        val economics = repository.getNetworkEconomics()

        return Stats(
            epoch = when (statistics) {
                is StatsRepositoryResponseModel.Success ->
                    statistics.stats.epoch.toString()
                else -> TODO()
            },
            price = when (economics) {
                is EconomicsRepositoryResponseModel.Success ->
                    economics.economics.price.toString()
                else -> TODO()
            }
        )

    }

}