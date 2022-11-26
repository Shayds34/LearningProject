package com.sederikkuapplication.shared.domain.usecase.model.accountdetailed

data class AccountDetailedUseCaseModel(
    val address: String,
    val balance: String,
    val nonce: Number,
    val shard: Number,
    val username: String?
)
