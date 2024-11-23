package com.sy.home_ui.home

import com.sy.common_domain.model.OutCome
import com.sy.common_domain.model.GeoName
import com.sy.home_ui.home_contents.today.TodayState

data class HomeState(
    val geoName: GeoName? = null,
    val citiesResult: OutCome<List<GeoName>>? = null,
    val isCityBottomSheetVisible: Boolean = false,
    val todayState: TodayState = TodayState()
)
