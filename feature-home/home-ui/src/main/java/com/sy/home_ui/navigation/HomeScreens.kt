package com.sy.home_ui.navigation

private const val BaseName = "home"

sealed class HomeScreens(val route: String) {
    data object Main : HomeScreens("${BaseName}_main")
}