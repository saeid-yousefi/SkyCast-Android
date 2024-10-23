package com.sy.skycast.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sy.onboarding_ui.navigation.onboardingNavGraph

const val MAIN_NAV_GRAPH_ROUTE = "main_nav_graph"

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MAIN_NAV_GRAPH_ROUTE) {
        onboardingNavGraph(navController)
    }
}