package com.sy.home_data.model.dto

import com.sy.common_domain.model.weather.Wind
import kotlinx.serialization.Serializable

@Serializable
data class WindResponse(val speed: Float?, val deg: Int?) {
    fun toWind() = Wind(speed, deg)
}
