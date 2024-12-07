package com.sy.common_domain.model.weather

import kotlinx.serialization.Serializable

@Serializable
data class Wind(val speed: Float?, val degree: Int?)
