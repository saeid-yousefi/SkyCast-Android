package com.sy.home_domain.usecase

import com.sy.common_domain.usecase.ActionUseCase
import com.sy.home_domain.model.GeoName
import com.sy.home_domain.repository.HomeRepository

class SaveCityUseCase(private val repository: HomeRepository) :
    ActionUseCase<GeoName>() {
    override suspend fun execute(params: GeoName) {
        repository.saveCity(params)
    }
}