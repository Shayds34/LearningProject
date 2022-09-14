package com.sederikkuapplication.projects.proteo.fragments

import com.sederikkuapplication.network.elrond.TokenDetailed

data class ProteoMainUiModel(
    val state: State
) {

    sealed class State {
        object Loading: State()

        data class  Success(
            val token: TokenDetailed,
            val proteoPrice: String,
            val index: String
        ): State()
    }
}