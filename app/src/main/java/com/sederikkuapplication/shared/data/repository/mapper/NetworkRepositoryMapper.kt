package com.sederikkuapplication.shared.data.repository.mapper

import com.example.common.network.GenericFailureCauseRepositoryModel
import com.example.common.network.NetworkResponse
import com.example.common.network.Response
import com.sederikkuapplication.shared.data.network.responsemodels.EconomicsApiResponseModel
import com.sederikkuapplication.shared.data.network.responsemodels.StatsApiResponseModel
import com.sederikkuapplication.shared.data.repository.models.economics.EconomicsRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.economics.EconomicsRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.stats.StatsRepositoryModel
import com.sederikkuapplication.shared.data.repository.models.stats.StatsRepositoryResponseModel
import javax.inject.Inject

class NetworkRepositoryMapper @Inject constructor(){

    /**
     * map NETWORK ECONOMICS from Api to Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<EconomicsApiResponseModel>
    ): EconomicsRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                EconomicsRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: EconomicsApiResponseModel
    ): EconomicsRepositoryResponseModel {
        return EconomicsRepositoryResponseModel.Success(
            economics = EconomicsRepositoryModel(
                totalSupply = apiModel.totalSupply,
                circulatingSupply = apiModel.circulatingSupply,
                staked = apiModel.staked,
                price = apiModel.price,
                marketCap = apiModel.marketCap,
                apr = apiModel.apr,
                topUpApr = apiModel.topUpApr,
                baseApr = apiModel.baseApr,
                minimumAuctionTopUp = apiModel.minimumAuctionTopUp,
                tokenMarketCap = apiModel.tokenMarketCap
            )
        )
    }


    /**
     * map NETWORK STATISTICS from Api to Repository
     */
    fun mapApiToRepository(
        response: NetworkResponse<StatsApiResponseModel>
    ): StatsRepositoryResponseModel {
        return when (response) {
            is Response.Success -> mapToSuccess(response.successValue)
            is Response.Failure -> {
                StatsRepositoryResponseModel.GenericFailure(
                    cause = GenericFailureCauseRepositoryModel.UNKNOWN(
                        sourceThrowable = Throwable(response.cause.localizedMessage)
                    )
                )
            }
        }
    }

    private fun mapToSuccess(
        apiModel: StatsApiResponseModel
    ): StatsRepositoryResponseModel {
        return StatsRepositoryResponseModel.Success(
            stats = StatsRepositoryModel(
                accounts = apiModel.accounts,
                blocks = apiModel.blocks,
                epoch = apiModel.epoch,
                refreshRate = apiModel.refreshRate,
                roundsPassed = apiModel.roundsPassed,
                roundsPerEpoch = apiModel.roundsPerEpoch,
                shards = apiModel.shards,
                transactions = apiModel.transactions
            )
        )
    }

}