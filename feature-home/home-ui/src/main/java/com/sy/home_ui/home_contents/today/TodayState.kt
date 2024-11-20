package com.sy.home_ui.home_contents.today

import com.sy.common_domain.model.OutCome
import com.sy.home_domain.model.weather.CurrentWeather

data class TodayState(
    val currentWeatherResult: OutCome<CurrentWeather>? = null,
    val todayDate: String = ""
)
