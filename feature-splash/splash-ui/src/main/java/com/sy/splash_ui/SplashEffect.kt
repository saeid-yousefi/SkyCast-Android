package com.sy.splash_ui

sealed class SplashEffect {
    data class NavigateTo(val route: String) : SplashEffect()
}