package com.sy.home_ui.home

sealed class HomeAction {
    data class ChangeCityBottomSheetVisibility(val isVisible: Boolean) : HomeAction()
}