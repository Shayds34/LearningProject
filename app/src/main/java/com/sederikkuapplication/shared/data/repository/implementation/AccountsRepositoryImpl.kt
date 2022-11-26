package com.sederikkuapplication.shared.data.repository.implementation

import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.shared.data.network.ElrondApi
import com.sederikkuapplication.shared.data.repository.mapper.AccountsRepositoryMapper
import com.sederikkuapplication.shared.data.repository.models.accountdetailed.AccountDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.domain.repository.AccountsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class AccountsRepositoryImpl @Inject constructor(
    private val api: ElrondApi,
    private val mapper: AccountsRepositoryMapper,
    @Named(DispatchersName.DATA) private val dispatcher: CoroutineDispatcher
) : AccountsRepository {

    override suspend fun getAccountDetails(address: String) =
        withContext(dispatcher) {
            val apiResponse = api.getAccountDetails((address))
            mapper.mapApiToRepository(apiResponse)
        }

    override suspend fun getAccountTokens(
        address: String,
        from: Number?,
        size: Number?,
        search: String?,
        name: String?,
        identifier: String?
    ) = withContext(dispatcher) {
        val apiResponse = api.getAccountTokens(
            address = address,
            from = from,
            size = size,
            search = search,
            name = name,
            identifier = identifier
        )
        mapper.mapApiToRepository(apiResponse)
    }

}