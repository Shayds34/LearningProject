package com.sederikkuapplication.shared.domain.repository

import com.sederikkuapplication.shared.data.repository.models.accountdetailed.AccountDetailedRepositoryResponseModel
import com.sederikkuapplication.shared.data.repository.models.tokendetailed.ListTokenDetailedRepositoryResponseModel

interface AccountsRepository {

    /**
     * ACCOUNT DETAILS
     * > Returns account details for a given address
     */
    suspend fun getAccountDetails(
        address: String
    ) : AccountDetailedRepositoryResponseModel

    /**
     * GET ACCOUNT TOKENS
     * > Returns a list of all available fungible tokens for a given address, together with their balance
     */
    suspend fun getAccountTokens(
        address: String,
        from: Number?,
        size: Number?,
        search: String?,
        name: String?,
        identifier: String?
    ) : ListTokenDetailedRepositoryResponseModel

}