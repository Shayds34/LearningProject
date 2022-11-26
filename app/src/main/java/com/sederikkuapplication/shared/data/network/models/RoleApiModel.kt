package com.sederikkuapplication.shared.data.network.models

data class RoleApiModel(
    val address: String,
    val canLocalMint: Boolean = false,
    val canLocalBurn: Boolean = false,
    val roles: List<String>?
)
