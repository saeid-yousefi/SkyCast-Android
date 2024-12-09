package com.sy.home_domain.usecase

import com.sy.common_domain.model.weather.WeatherInfo
import com.sy.common_domain.usecase.ResultUseCase
import com.sy.home_domain.repository.HomeRepository

class GetForeCastUseCase(private val repository: HomeRepository) :
    ResultUseCase<String, List<WeatherInfo>>() {
    override suspend fun execute(params: String): List<WeatherInfo> {
        return repository.getForeCast(params)
    }
}