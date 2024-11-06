package com.sy.home_data.data_source.remote.data

import com.sy.home_data.data_source.remote.api.HomeApiService
import com.sy.home_data.model.dto.GeoNameResponse

class HomeRemoteDataSourceImpl(private val homeApiService: HomeApiService) : HomeRemoteDataSource {

    override suspend fun searchCity(cityName: String): List<GeoNameResponse> {
        return homeApiService.searchCity(cityName)
    }
}