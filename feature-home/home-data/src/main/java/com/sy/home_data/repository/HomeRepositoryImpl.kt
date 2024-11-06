package com.sy.home_data.repository

import com.sy.home_data.data_source.remote.data.HomeRemoteDataSource
import com.sy.home_domain.model.GeoName
import com.sy.home_domain.repository.HomeRepository

class HomeRepositoryImpl(private val remoteDataSource: HomeRemoteDataSource) : HomeRepository {
    override suspend fun searchCity(cityName: String): List<GeoName> {
        return remoteDataSource.searchCity(cityName).map { it.toGeoName() }
    }
}