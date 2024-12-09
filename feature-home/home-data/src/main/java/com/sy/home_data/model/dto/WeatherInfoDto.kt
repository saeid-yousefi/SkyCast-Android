package com.sy.home_data.model.dto

import com.sy.common_domain.model.weather.WeatherInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherInfoDto(
    @SerialName("dt_txt") val dtText: String? = null,
    val dt: Long? = null,
    val weather: List<WeatherDto>,
    val main: MainDto,
    val wind: WindDto
) {
    fun toWeatherInfo() =
        WeatherInfo(
            weather = weather.map { it.toWeather() },
            main = main.toMain(),
            wind = wind.toWind(),
            timeInMillis = dt,
            timeText = dtText
        )
}
