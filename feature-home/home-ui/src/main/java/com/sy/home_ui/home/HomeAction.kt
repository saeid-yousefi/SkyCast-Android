package com.sy.home_ui.home

import com.sy.common_domain.model.GeoName

sealed class HomeAction {
    data class ChangeCityBottomSheetVisibility(val isVisible: Boolean) : HomeAction()
    data class GetCurrentWeather(val cityName: String) : HomeAction()
    data class GetInitialData(val cityName: String):HomeAction()
    data class GetForecast(val cityName: String) : HomeAction()
    data class ChangePagerState(val index: Int) : HomeAction()
    data class SaveCity(val geoName: GeoName) : HomeAction()
    data object GetCurrentDate : HomeAction()
}