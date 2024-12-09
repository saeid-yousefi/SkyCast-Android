package com.sy.home_domain.usecase

import com.sy.common_domain.usecase.ResultUseCase
import com.sy.common_domain.model.weather.WeatherInfo
import com.sy.home_domain.repository.HomeRepository

class GetWeatherInfoUseCase(private val repository: HomeRepository) :
    ResultUseCase<String, WeatherInfo>() {
    override suspend fun execute(params: String): WeatherInfo {
        return repository.getCurrentWeatherData(params)
    }
}