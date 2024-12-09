package com.sy.home_data.model.dto

import com.sy.common_domain.model.weather.Weather
import com.sy.common_domain.model.weather.WeatherType
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(val main: String, val description: String, val icon: String) {

    fun toWeather() = Weather(main, description, weatherStringToWeatherType(description))

    private fun weatherStringToWeatherType(apiWeather: String): WeatherType {
        return when (apiWeather.lowercase()) {
            "clear sky" -> WeatherType.SUNNY
            "few clouds", "scattered clouds", "broken clouds", "overcast clouds" -> WeatherType.CLOUDY
            "light rain", "moderate rain", "heavy intensity rain", "shower rain" -> WeatherType.RAINY
            "thunderstorm", "thunderstorm with light rain", "thunderstorm with heavy rain" -> WeatherType.STORMY
            "light snow", "snow", "heavy snow", "sleet", "rain and snow" -> WeatherType.SNOWY
            "mist", "haze", "fog" -> WeatherType.FOGGY
            else -> WeatherType.UNKNOWN
        }
    }
}

