package com.sy.home_data.data_source.remote.api

import com.sy.common_data.Constants
import com.sy.common_data.network.bodyOrThrow
import com.sy.home_data.model.dto.GeoNameResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class HomeApiServiceImpl(private val client: HttpClient) : HomeApiService {

    override suspend fun searchCity(cityName: String): List<GeoNameResponse> {
        val url = Constants.CITY_BASE_URL + "searchJSON"
        return bodyOrThrow {
            client.get(url) {
                parameter("q", cityName)
                parameter("username", "saeed.yousefi")
                parameter("appid", "813d5cdaf8e0511cf34204f364b2755e")
            }
        }
    }
}