package com.sy.home_ui.home

import com.sy.home_domain.model.GeoName

sealed class HomeAction {
    data class ChangeCityBottomSheetVisibility(val isVisible: Boolean) : HomeAction()
    data class ChangePagerState(val index: Int) : HomeAction()
    data class SaveCity(val geoName: GeoName) : HomeAction()
}