package com.sy.home_data.model.dto

import com.sy.common_domain.model.GeoName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeoNameDto(
    @SerialName("lng") var lng: String? = null,
    @SerialName("lat") var lat: String? = null,
    @SerialName("geonameId") var geoNameId: Int? = null,
    @SerialName("toponymName") var toponymName: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("countryName") var countryName: String? = null,
    @SerialName("adminName1") var state: String? = null,
) {
    fun toGeoName() = GeoName(lng, lat, geoNameId, toponymName, name, countryName, state)
}