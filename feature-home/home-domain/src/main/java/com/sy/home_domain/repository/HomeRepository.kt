package com.sy.home_domain.repository

import com.sy.common_domain.model.GeoName
import com.sy.common_domain.model.weather.WeatherInfo
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun searchCity(cityName: String): List<GeoName>
    suspend fun observeCity(): Flow<GeoName?>
    suspend fun saveCity(geoName: GeoName)
    suspend fun getCurrentWeatherData(cityName: String): WeatherInfo
    suspend fun getForeCast(cityName: String): List<WeatherInfo>
}