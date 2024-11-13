package com.sy.home_domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GeoName(
    val lng: String? = null,
    val lat: String? = null,
    val geoNameId: Int? = null,
    val toponymName: String? = null,
    val name: String? = null,
    val countryName: String? = null,
    val state: String? = null,
)
