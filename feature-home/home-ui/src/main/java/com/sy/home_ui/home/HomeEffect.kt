package com.sy.home_ui.home

sealed class HomeEffect {
    data class ShowSnackBarWithAction(val action: HomeAction) : HomeEffect()
}