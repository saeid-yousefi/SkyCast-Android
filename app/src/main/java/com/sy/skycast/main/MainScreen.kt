package com.sy.skycast.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sy.skycast.navigation.AppNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppNavHost(navController)
}