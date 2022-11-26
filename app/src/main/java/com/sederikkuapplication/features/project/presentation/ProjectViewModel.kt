package com.sederikkuapplication.features.project.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sederikkuapplication.features.project.domain.models.ProjectUiState
import com.sederikkuapplication.modules.DispatchersName
import com.sederikkuapplication.shared.domain.usecases.TokensUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val tokenUsesCases: TokensUseCases,
    @Named(DispatchersName.UI_VIEWMODEL) private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var projectUiState by mutableStateOf(ProjectUiState())
    private set

    suspend fun getProject(identifier: String) = try {
        val token = tokenUsesCases.getTokenDetailed(identifier)
        val lockedAccounts = tokenUsesCases.getTeamAccounts(identifier)
        projectUiState = projectUiState.copy(
            isLoading = false,
            token = token,
            lockedAccounts = lockedAccounts
        )
    } catch (ioe: IOException) {
        TODO()
    }

}
