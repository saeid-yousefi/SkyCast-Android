package com.sy.skycast.di

import com.sy.home_data.data_source.remote.HomeRemoteDataSource
import com.sy.home_data.data_source.remote.HomeRemoteDataSourceImpl
import com.sy.home_data.repository.HomeRepositoryImpl
import com.sy.home_domain.repository.HomeRepository
import com.sy.home_domain.usecase.GetCurrentDateUseCase
import com.sy.home_domain.usecase.ObserveCityUseCase
import com.sy.home_domain.usecase.SaveCityUseCase
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    singleOf(::HomeRemoteDataSourceImpl) bind HomeRemoteDataSource::class
    singleOf(::HomeRepositoryImpl) bind HomeRepository::class
    factoryOf(::SearchCityUseCase)
    factoryOf(::ObserveCityUseCase)
    factoryOf(::SaveCityUseCase)
    factoryOf(::GetCurrentDateUseCase)
    viewModelOf(::HomeViewModel)
}