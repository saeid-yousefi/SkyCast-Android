package com.sy.home_domain.usecase

import com.sy.common_domain.usecase.ResultUseCase
import com.sy.home_domain.model.GeoName
import com.sy.home_domain.repository.HomeRepository

class SearchCityUseCase(private val repository: HomeRepository) :
    ResultUseCase<String, List<GeoName>>() {
    override suspend fun execute(params: String): List<GeoName> {
        return repository.searchCity(params)
    }
}