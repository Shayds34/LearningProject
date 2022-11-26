package com.sederikkuapplication.features.project

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sederikkuapplication.features.Screen
import com.sederikkuapplication.features.home.presentation.HomeScreen
import com.sederikkuapplication.features.home.presentation.HomeViewModel
import com.sederikkuapplication.features.project.presentation.ProjectViewModel
import com.sederikkuapplication.features.project.presentation.ProjectScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route ) {
        composable(route = Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.ProjectScreen.route + "/{identifier}",
            arguments = listOf(
                navArgument("identifier") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable  = true
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<ProjectViewModel>()
            ProjectScreen(viewModel = viewModel, identifier = entry.arguments?.getString("identifier"))
        }
    }
}