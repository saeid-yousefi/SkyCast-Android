package com.sy.splash_ui

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashState, SplashEffect, SplashAction>() {
    override fun createInitialState(): SplashState {
        return SplashState(1)
    }

    override fun updateTextInput(inputId: Int, value: String) {}

    override fun submitAction(action: SplashAction) {
        viewModelScope.launch {
            when (action) {

                else -> {}
            }
        }
    }
}