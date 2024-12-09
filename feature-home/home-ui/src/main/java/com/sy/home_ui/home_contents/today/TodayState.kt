package com.sy.home_ui.home_contents.today

import com.sy.common_domain.model.OutCome
import com.sy.common_domain.model.weather.WeatherInfo

data class TodayState(
    val forecastResult: OutCome<List<WeatherInfo>>? = null,
    val weatherInfoResult: OutCome<WeatherInfo>? = null,
    val weatherInfo: WeatherInfo? = null,
    val forecast: List<WeatherInfo>? = null,
    val todayDate: String = ""
)
