package com.sy.home_ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.sy.common_ui.graphs.RootGraphs
import com.sy.home_ui.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
) {
    navigation(startDestination = HomeScreens.Main.route, route = RootGraphs.Home.route) {
        composable(route = HomeScreens.Main.route) {
            HomeScreen(navController = navController)
        }
    }
}