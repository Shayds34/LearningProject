package com.sederikkuapplication.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sederikkuapplication.features.home.domain.usecases.NetworkUseCase
import com.sederikkuapplication.features.home.domain.models.HomeUiState
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.shared.domain.usecases.TokensUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: NetworkUseCase,
    private val tokensUseCases: TokensUseCases,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
    private set

    init {
        viewModelScope.launch(dispatcher) {
            getStats()
        }
    }

    private suspend fun getStats() = try {
        val stats = useCases()
        val tokens = tokensUseCases.getTokens()
        uiState = uiState.copy(
            isLoading = false,
            stats = stats,
            tokens = tokens
        )
    } catch (ioe: IOException) {
        TODO()
    }


}