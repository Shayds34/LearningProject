package com.sederikkuapplication.proteo.mapper

import com.sederikkuapplication.network.elrond.AccountDetailed
import com.sederikkuapplication.proteo.usecase.model.accountdetailed.ProteoAccountDetailedUseCaseModel
import javax.inject.Inject

class AccountDetailedUiMapper @Inject constructor() {

    fun mapUseCaseToUi(useCaseModel: ProteoAccountDetailedUseCaseModel): AccountDetailed {
        return when (useCaseModel) {
            is ProteoAccountDetailedUseCaseModel.Success -> {
                AccountDetailed(
                    address = useCaseModel.accountDetailed.address,
                    balance = useCaseModel.accountDetailed.balance,
                    nonce = useCaseModel.accountDetailed.nonce,
                    shard = useCaseModel.accountDetailed.shard,
                    username = useCaseModel.accountDetailed.username
                )
            }
            is ProteoAccountDetailedUseCaseModel.GenericFailure ->
                TODO("Seb: AccountDetailedUiMapperGenericError ${useCaseModel.cause}")
        }
    }
}
