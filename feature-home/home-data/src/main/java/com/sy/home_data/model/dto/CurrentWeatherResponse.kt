package com.sy.home_data.model.dto

import com.sy.common_domain.model.weather.CurrentWeather
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(val weather: List<WeatherResponse>, val main: MainResponse) {
    fun toCurrentWeather() =
        CurrentWeather(weather = weather.map { it.toWeather() }, main = main.toMain())
}
