package com.sy.skycast.di

import com.sy.spash_data.repositories.SplashRepositoryImpl
import com.sy.spash_domain.repositories.SplashRepository
import com.sy.spash_domain.usecases.IsFirstLaunchUseCase
import com.sy.spash_domain.usecases.SetFirstLaunchUseCase
import com.sy.splash_ui.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    single<SplashRepository> { SplashRepositoryImpl(get()) }
    factory { IsFirstLaunchUseCase(get()) }
    factory { SetFirstLaunchUseCase(get()) }
    viewModel { SplashViewModel(get(), get()) }
}