package com.sy.onboarding_ui.navigation

private const val BaseName = "onboarding"

sealed class OnBoardingScreens(val route: String) {
    data object NavGraph : OnBoardingScreens("${BaseName}_nav_graph")
    data object Main : OnBoardingScreens("${BaseName}_main")
}