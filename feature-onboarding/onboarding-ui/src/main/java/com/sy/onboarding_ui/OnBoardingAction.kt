package com.sy.onboarding_ui

sealed class OnBoardingAction {
    data class NavigateTo(val route: String) : OnBoardingAction()
    data object NextOnBoard : OnBoardingAction()
}