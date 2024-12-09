package com.sy.home_data.data_source.remote

import com.sy.common_data.Constants
import com.sy.common_data.network.bodyOrThrow
import com.sy.home_data.model.dto.WeatherInfoDto
import com.sy.home_data.model.dto.ForecastDto
import com.sy.home_data.model.dto.SearchCityDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class HomeRemoteDataSourceImpl(private val client: HttpClient) : HomeRemoteDataSource {

    override suspend fun searchCity(cityName: String): SearchCityDto {
        val url = Constants.CITY_BASE_URL + "searchJSON"
        return bodyOrThrow {
            client.get(url) {
                parameter("q", cityName)
                parameter("username", "saeed.yousefi")
                parameter("appid", "813d5cdaf8e0511cf34204f364b2755e")
            }
        }
    }

    override suspend fun getCurrentWeatherData(cityName: String): WeatherInfoDto {
        val url = Constants.WEATHER_BASE_URL + "data/2.5/weather"
        return bodyOrThrow {
            client.get(url) {
                parameter(Constants.WEATHER_APP_ID_KEY, Constants.WEATHER_APP_ID)
                parameter("q", cityName)
                parameter("units", "metric")
            }
        }
    }

    override suspend fun getForecast(cityName: String): ForecastDto {
        val url = Constants.WEATHER_BASE_URL + "/data/2.5/forecast"
        return bodyOrThrow {
            client.get(url) {
                parameter(Constants.WEATHER_APP_ID_KEY, Constants.WEATHER_APP_ID)
                parameter("q", cityName)
                parameter("units", "metric")
            }
        }
    }
}