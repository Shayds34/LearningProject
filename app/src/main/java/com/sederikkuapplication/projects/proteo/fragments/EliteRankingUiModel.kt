package com.sederikkuapplication.projects.proteo.fragments

import com.sederikkuapplication.projects.proteo.model.EliteDetailedModelUi


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