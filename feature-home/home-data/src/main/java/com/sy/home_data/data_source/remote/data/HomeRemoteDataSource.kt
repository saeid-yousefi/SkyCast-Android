package com.sy.home_data.data_source.remote.data

import com.sy.home_data.model.dto.GeoNameResponse

interface HomeRemoteDataSource {
    suspend fun searchCity(cityName: String): List<GeoNameResponse>

}