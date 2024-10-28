package com.sy.onboarding_ui

sealed class OnBoardingEffect {
    data class NavigateTo(val route: String) : OnBoardingEffect()
}