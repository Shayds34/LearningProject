package com.sederikkuapplication.projects.proteo.mapper

import com.sederikkuapplication.network.elrond.transaction.Transaction
import com.sederikkuapplication.projects.proteo.usecase.model.transferlist.ProteoListTransfersUseCaseModel
import javax.inject.Inject

class TransfersUiMapper @Inject constructor() {

    fun mapUseCaseToUi(useCaseModel: ProteoListTransfersUseCaseModel): List<Transaction> {
        return when (useCaseModel) {
            is ProteoListTransfersUseCaseModel.Success -> {
                useCaseModel.transfers.map {
                    it
                }
            }
            is ProteoListTransfersUseCaseModel.GenericFailure ->
                TODO("Seb: TransactionsUiMapperGenericError ${useCaseModel.cause}")
        }
    }
}
