package com.sy.home_domain.repository

import com.sy.home_domain.model.GeoName
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun searchCity(cityName: String): List<GeoName>
    suspend fun observeCity(): Flow<GeoName?>
    suspend fun saveCity(geoName: GeoName)
}