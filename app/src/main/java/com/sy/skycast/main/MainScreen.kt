package com.sy.skycast.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sy.skycast.navigation.AppNavHost
import com.sy.skycast.navigation.MAIN_NAV_GRAPH_ROUTE

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppNavHost(navController)
}