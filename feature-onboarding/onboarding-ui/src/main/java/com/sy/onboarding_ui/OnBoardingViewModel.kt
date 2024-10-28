package com.sy.onboarding_ui

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import kotlinx.coroutines.launch

class OnBoardingViewModel : BaseViewModel<OnBoardingState, OnBoardingEffect, OnBoardingAction>() {

    override fun createInitialState(): OnBoardingState {
        return OnBoardingState()
    }

    override fun updateTextInput(inputId: Int, value: String) {}

    override fun submitAction(action: OnBoardingAction) {
        viewModelScope.launch {
            when (action) {
                is OnBoardingAction.NextOnBoard -> {
                    val currentPageIndex = currentState.currentPageIndex + 1
                    if (currentPageIndex >= 4) {

                    } else {
                        setState {
                            copy(
                                currentPageIndex = currentPageIndex,
                                onBoardingPage = OnBoardingPages[currentPageIndex]
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}