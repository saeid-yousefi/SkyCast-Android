package com.sy.home_data.model.dto

import com.sy.common_domain.model.weather.Main
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainResponse(
    val temp: Float,
    @SerialName("temp_min") val tempMin: Float?,
    @SerialName("temp_max") val tempMax: Float?,
    val humidity: Float?
) {
    fun toMain() = Main(temp, tempMin, tempMax, humidity)
}
