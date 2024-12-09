package com.sy.home_data.data_source.remote

import com.sy.home_data.model.dto.WeatherInfoDto
import com.sy.home_data.model.dto.ForecastDto
import com.sy.home_data.model.dto.SearchCityDto

interface HomeRemoteDataSource {
    suspend fun searchCity(cityName: String): SearchCityDto
    suspend fun getCurrentWeatherData(cityName: String): WeatherInfoDto
    suspend fun getForecast(cityName: String): ForecastDto
}
