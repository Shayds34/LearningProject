package com.sederikkuapplication.projects.proteo.composables

sealed class Screen( val route: String) {
    object MainScreen : Screen("main_screen")
    object UnstakeScreen : Screen("unstake_screen")


}
