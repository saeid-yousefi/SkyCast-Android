package com.sy.home_domain.usecase

import com.sy.common_domain.usecase.ResultUseCase
import com.sy.home_domain.model.weather.CurrentWeather
import com.sy.home_domain.repository.HomeRepository

class GetCurrentWeatherDataUseCase(private val repository: HomeRepository) :
    ResultUseCase<String, CurrentWeather>() {
    override suspend fun execute(params: String): CurrentWeather {
        return repository.getCurrentWeatherData(params)
    }
}