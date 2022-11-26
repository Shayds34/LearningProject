package com.sederikkuapplication.shared.domain.models

data class TokenTeamAccount(
    val isLocked: Boolean = false,
    val address: String,
    val shorthenAddress: String,
    val name: String,
    val balance: String,
    val pngUrl: String?
)