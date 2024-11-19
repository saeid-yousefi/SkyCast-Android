package com.sy.home_data.data_source.remote.api

import com.sy.home_data.model.dto.CurrentWeatherResponse
import com.sy.home_data.model.dto.SearchCityResponse

interface HomeApiService {
    suspend fun searchCity(cityName: String): SearchCityResponse
    suspend fun getCurrentWeatherData(cityName: String): CurrentWeatherResponse
}
