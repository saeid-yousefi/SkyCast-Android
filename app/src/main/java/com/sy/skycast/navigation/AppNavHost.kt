package com.sy.skycast.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sy.home_ui.navigation.homeNavGraph
import com.sy.onboarding_ui.navigation.onboardingNavGraph
import com.sy.splash_ui.SplashScreen
import com.sy.splash_ui.navigation.SplashScreens

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = SplashScreens.Main.route) {
        composable(route = SplashScreens.Main.route) {
            SplashScreen(navController)
        }
        onboardingNavGraph(navController)
        homeNavGraph(navController)
    }
}