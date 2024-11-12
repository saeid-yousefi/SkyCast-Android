package com.sy.home_ui.home

sealed class HomeAction {
    data class ChangeCityBottomSheetVisibility(val isVisible: Boolean) : HomeAction()
    data class UpdateTextInput(val text: String?, val id: Int) : HomeAction()
}