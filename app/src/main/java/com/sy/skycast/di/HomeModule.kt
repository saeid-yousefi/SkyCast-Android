package com.sy.skycast.di

import com.sy.home_data.data_source.remote.api.HomeApiService
import com.sy.home_data.data_source.remote.api.HomeApiServiceImpl
import com.sy.home_data.data_source.remote.data.HomeRemoteDataSource
import com.sy.home_data.data_source.remote.data.HomeRemoteDataSourceImpl
import com.sy.home_data.repository.HomeRepositoryImpl
import com.sy.home_domain.repository.HomeRepository
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    singleOf(::HomeApiServiceImpl) bind HomeApiService::class
    singleOf(::HomeRemoteDataSourceImpl) bind HomeRemoteDataSource::class
    singleOf(::HomeRepositoryImpl) bind HomeRepository::class
    factoryOf(::SearchCityUseCase)
    viewModelOf(::HomeViewModel)
}