package com.sy.common_domain.model.weather

import kotlinx.serialization.Serializable

@Serializable
data class Weather(val main: String, val description: String, val weatherType: WeatherType)
