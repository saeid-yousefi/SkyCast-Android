package com.sy.home_data.data_source.remote.api

import com.sy.home_data.model.dto.GeoNameResponse

interface HomeApiService {
    suspend fun searchCity(cityName: String): List<GeoNameResponse>
}
