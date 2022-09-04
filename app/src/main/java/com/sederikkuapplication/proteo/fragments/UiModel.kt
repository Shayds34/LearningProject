package com.sederikkuapplication.proteo.fragments

import com.sederikkuapplication.proteo.model.TransactionModelUi

data class UiModel(
    val state: State
) {

    sealed class State {
        object Loading: State()

        data class Success(
            val adapterItems: List<TransactionModelUi>,
            val dailyCount: String,
            val epochOnDisplay: String
        ): State()
    }
}