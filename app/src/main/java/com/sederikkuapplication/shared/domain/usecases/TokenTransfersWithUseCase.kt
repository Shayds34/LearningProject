package com.sederikkuapplication.shared.domain.usecases

import com.sederikkuapplication.shared.data.repository.models.transaction.ListTransactionRepositoryResponseModel
import com.sederikkuapplication.shared.domain.repository.TokensRepository
import javax.inject.Inject

class TokenTransfersWithUseCase @Inject constructor(
    private val repository: TokensRepository
) {
    private val jexWallet = "erd1qqqqqqqqqqqqqpgqawkm2tlyyz6vtg02fcr5w02dyejp8yrw0y8qlucnj2"


    suspend operator fun invoke(
        identifier: String,
        address: String
    ) {
        val transactionsIn = repository.getTokenTransfers(
            identifier = identifier,
            from = 0,
            size = 10000,
            sender = null,
            receiver = address,
            senderShard = null,
            receiverShard = null,
            miniBlockHash = null,
            hashes = null,
            status = "success",
            search = null,
            before = null,
            after = null,
            order = null
        )
        val transactionOut = repository.getTokenTransfers(
            identifier = identifier,
            from = 0,
            size = 10000,
            sender = address,
            receiver = null,
            senderShard = null,
            receiverShard = null,
            miniBlockHash = null,
            hashes = null,
            status = null,
            search = null,
            before = null,
            after = null,
            order = null
        )
    }
}