package com.sy.home_domain.usecase

import com.sy.common_domain.usecase.ValueUseCase
import com.sy.home_domain.model.GeoName
import com.sy.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class ObserveCityUseCase(private val repository: HomeRepository) :
    ValueUseCase<Unit, Flow<GeoName?>>() {
    override suspend fun execute(params: Unit): Flow<GeoName?> {
        return repository.observeCity()
    }
}