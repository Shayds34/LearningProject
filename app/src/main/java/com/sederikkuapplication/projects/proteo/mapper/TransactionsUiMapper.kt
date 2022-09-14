package com.sederikkuapplication.projects.proteo.mapper

import com.sederikkuapplication.network.elrond.transaction.Transaction
import com.sederikkuapplication.projects.proteo.usecase.model.transactionlist.ProteoListTransactionsUseCaseModel
import javax.inject.Inject

class TransactionsUiMapper @Inject constructor() {

    fun mapUseCaseToUi(useCaseModel: ProteoListTransactionsUseCaseModel): List<Transaction> {
        return when (useCaseModel) {
            is ProteoListTransactionsUseCaseModel.Success -> {
                useCaseModel.transactions.map {
                    it
                }
            }
            is ProteoListTransactionsUseCaseModel.GenericFailure ->
                TODO("Seb: TransactionsUiMapperGenericError ${useCaseModel.cause}")
        }
    }
}
