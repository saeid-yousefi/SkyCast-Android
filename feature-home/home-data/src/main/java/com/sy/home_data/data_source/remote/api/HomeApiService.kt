package com.sy.home_data.data_source.remote.api

import com.sy.home_data.model.dto.SearchCityResponse

interface HomeApiService {
    suspend fun searchCity(cityName: String): SearchCityResponse
}
