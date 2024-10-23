package com.sy.onboarding_ui.navigation

sealed class OnBoardingScreens(val route: String) {
    data object NavGraph : OnBoardingScreens("onboarding")
    data object Main : OnBoardingScreens("onboarding_main")
}