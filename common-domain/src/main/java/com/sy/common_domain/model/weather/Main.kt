package com.sy.common_domain.model.weather

import kotlinx.serialization.Serializable

@Serializable
data class Main(
    val temp: Float,
    val feelsLike: Float?,
    val tempMin: Float?,
    val tempMax: Float?,
    val humidity: Float?,
)
