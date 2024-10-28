package com.sy.onboarding_ui

import com.sy.common_ui.base.BaseViewModel

class OnBoardingViewModel : BaseViewModel<OnBoardingState, OnBoardingEffect, OnBoardingAction>() {

    override fun createInitialState(): OnBoardingState {
        return OnBoardingState()
    }

    override fun updateTextInput(inputId: Int, value: String) {}

    override fun submitAction(action: OnBoardingAction) {}
}