package com.sederikkuapplication.projects.proteo.fragments

import com.sederikkuapplication.network.elrond.AccountDetailed

data class AccountUiModel(
    val state: State
) {

    sealed class State {
        object Loading : State()

        data class Success(
            val accountDetailed: AccountDetailed,
            val proteoInWallet: String,
            val sProteoInWallet: String,
            val eliteBalance: String,
            val egldInStaking: String,
            val usdcInStaking: String
        ) : State()
    }
}
