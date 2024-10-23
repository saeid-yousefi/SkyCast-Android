package com.sy.splash_ui.navigation

private const val BaseName = "splash"

sealed class SplashScreens(val route: String) {
    data object Main : SplashScreens("${BaseName}_main")
}