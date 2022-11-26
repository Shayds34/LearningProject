package com.sederikkuapplication.features

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ProjectScreen : Screen("project_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}