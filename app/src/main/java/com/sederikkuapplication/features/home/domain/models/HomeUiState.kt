package com.sederikkuapplication.features.home.domain.models

data class HomeUiState(
    val isLoading: Boolean = false,
    val stats: Stats? = null,
    val tokens: List<Token> = emptyList()
)