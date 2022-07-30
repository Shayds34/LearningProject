package com.sederikkuapplication.network.elrond.transaction

data class Role(
    val address: String,
    val canLocalMint: Boolean = false,
    val canLocalBurn: Boolean = false,
    val roles: List<String>
)
