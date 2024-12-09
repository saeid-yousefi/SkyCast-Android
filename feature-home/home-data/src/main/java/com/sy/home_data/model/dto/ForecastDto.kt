package com.sy.home_data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ForecastDto(val list: List<WeatherInfoDto>)
