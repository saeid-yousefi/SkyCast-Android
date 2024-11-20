package com.sy.home_data.model.dto

import com.sy.home_domain.model.weather.Weather
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(val main: String, val description: String, val icon: String) {
    fun toWeather() = Weather(main, description, icon)
}
