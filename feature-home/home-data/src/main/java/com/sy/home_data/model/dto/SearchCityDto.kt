package com.sy.home_data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCityDto(
    @SerialName("geonames") val geoNames: List<GeoNameDto>
)
