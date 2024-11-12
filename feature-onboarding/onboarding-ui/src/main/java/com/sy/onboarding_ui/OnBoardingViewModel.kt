package com.sy.onboarding_ui

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import com.sy.common_ui.graphs.RootGraphs
import com.sy.spash_domain.usecases.SetFirstLaunchUseCase
import kotlinx.coroutines.launch

class OnBoardingViewModel constructor(private val setFirstLaunchUseCase: SetFirstLaunchUseCase) :
    BaseViewModel<OnBoardingState, OnBoardingEffect, OnBoardingAction>() {

    override fun createInitialState(): OnBoardingState {
        return OnBoardingState()
    }

    override suspend fun updateTextInput(inputId: Int, text: String?) {}

    override fun submitAction(action: OnBoardingAction) {
        viewModelScope.launch {
            when (action) {
                is OnBoardingAction.NextOnBoard -> {
                    val currentPageIndex = currentState.currentPageIndex + 1
                    if (currentPageIndex >= 4) {
                        setFirstLaunchUseCase(false)
                        setEffect(OnBoardingEffect.NavigateTo(RootGraphs.Home.route))

                    } else {
                        setState {
                            copy(
                                currentPageIndex = currentPageIndex,
                            )
                        }
                    }
                }

                is OnBoardingAction.PreviousOnBoard -> {
                    val currentPageIndex = currentState.currentPageIndex - 1
                    setState { copy(currentPageIndex = currentPageIndex) }
                }

                else -> {}
            }
        }
    }
}