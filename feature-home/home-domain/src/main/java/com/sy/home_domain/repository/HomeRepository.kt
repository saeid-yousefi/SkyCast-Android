package com.sy.home_domain.repository

import com.sy.home_domain.model.GeoName

interface HomeRepository {
    suspend fun searchCity(cityName: String): List<GeoName>
}