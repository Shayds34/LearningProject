package com.sederikkuapplication.proteo.mapper

import com.sederikkuapplication.network.network.Stats
import com.sederikkuapplication.proteo.usecase.model.stats.ProteoStatsUseCaseModel
import javax.inject.Inject

class StatsUiMapper @Inject constructor() {

    fun mapUseCaseToUi(useCaseModel: ProteoStatsUseCaseModel): Stats {
        return when (useCaseModel) {
            is ProteoStatsUseCaseModel.Success -> {
                Stats(
                    accounts = useCaseModel.stats.accounts,
                    blocks = useCaseModel.stats.blocks,
                    epoch = useCaseModel.stats.epoch,
                    refreshRate = useCaseModel.stats.refreshRate,
                    roundsPassed = useCaseModel.stats.roundsPassed,
                    roundsPerEpoch = useCaseModel.stats.roundsPerEpoch,
                    shards = useCaseModel.stats.shards,
                    transactions = useCaseModel.stats.transactions
                )
            }
            is ProteoStatsUseCaseModel.GenericFailure ->
                TODO("Seb: StatsUiMapperGenericError ${useCaseModel.cause}")
        }
    }


}