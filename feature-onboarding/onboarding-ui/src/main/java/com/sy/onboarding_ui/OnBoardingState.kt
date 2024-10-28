package com.sy.onboarding_ui

data class OnBoardingState(
    val currentPageIndex: Int = 0,
    val onBoardingPage: OnBoardingPage = OnBoardingPages.first(),
)
