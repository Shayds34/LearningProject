package com.sederikkuapplication.proteo.fragments

import com.sederikkuapplication.proteo.model.EliteDetailedModelUi


data class EliteRankingUiModel(
    val state: State
){

    sealed class State {
        object Loading: State()

        data class Success(
            val adapterItems: List<EliteDetailedModelUi>
        ): State()
    }
}