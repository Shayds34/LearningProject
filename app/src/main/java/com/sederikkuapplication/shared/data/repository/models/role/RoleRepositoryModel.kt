package com.sederikkuapplication.shared.data.repository.models.role

data class RoleRepositoryModel(
    val address: String,
    val canLocalMint: Boolean = false,
    val canLocalBurn: Boolean = false,
    val roles: List<String>?
)