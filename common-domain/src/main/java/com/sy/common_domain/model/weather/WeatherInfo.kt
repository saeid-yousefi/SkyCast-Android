package com.sy.common_domain.model.weather

data class WeatherInfo(
    val main: Main,
    val wind: Wind,
    val timeText: String?,
    val timeInMillis: Long?,
    val weather: List<Weather>,
)
