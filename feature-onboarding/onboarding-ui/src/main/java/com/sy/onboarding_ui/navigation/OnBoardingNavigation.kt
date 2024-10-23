package com.sy.onboarding_ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sy.onboarding_ui.OnBoardingScreen

fun NavGraphBuilder.onboardingNavGraph(navController: NavController) {
    navigation(
        startDestination = OnBoardingScreens.Main.route,
        route = OnBoardingScreens.NavGraph.route
    ) {
        composable(OnBoardingScreens.Main.route) {
            OnBoardingScreen()
        }
    }
}
