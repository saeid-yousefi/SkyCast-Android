package com.sy.splash_ui

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import com.sy.common_ui.graphs.RootGraphs
import com.sy.spash_domain.usecases.IsFirstLaunchUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val isFirstLaunchUseCase: IsFirstLaunchUseCase,
    private val setIsFirstLaunchUseCase: IsFirstLaunchUseCase
) : BaseViewModel<SplashState, SplashEffect, SplashAction>() {

    init {
        viewModelScope.launch {
            checkIsFirstLaunch()
        }
    }

    override fun createInitialState(): SplashState {
        return SplashState(1)
    }

    override suspend fun updateTextInput(inputId: Int, text: String?) {}

    override fun submitAction(action: SplashAction) {
        viewModelScope.launch {
            when (action) {

                else -> {}
            }
        }
    }

    private suspend fun checkIsFirstLaunch() {
        val isFirstLaunch = isFirstLaunchUseCase(Unit)
        if (isFirstLaunch) {
            delay(2000)
            setEffect(SplashEffect.NavigateTo(RootGraphs.OnBoarding.route))
        } else {
            setEffect(SplashEffect.NavigateTo(RootGraphs.Home.route))
        }
    }
}