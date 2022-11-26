package com.sederikkuapplication.features.project.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.sederikkuapplication.features.project.presentation.ProjectViewModel
import com.sederikkuapplication.presentation.mainboard.components.JexActivity
import com.sederikkuapplication.features.home.presentation.components.ProjectSupply
import com.sederikkuapplication.presentation.mainboard.components.TeamAccountsList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreen(
    viewModel: ProjectViewModel,
    identifier: String? = null
) {
    val token = viewModel.projectUiState.token
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = token?.name ?: "")
                },
                modifier = Modifier
                    .shadow(elevation = 20.dp),
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.inverseOnSurface,
                    titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            ProjectSupply(viewModel = viewModel)
            TeamAccountsList(viewModel = viewModel)
            JexActivity(viewModel = viewModel)
        }
    }
}