package com.sy.skycast.di

import com.sy.onboarding_data.repositories.OnBoardingRepositoryImpl
import com.sy.onboarding_domain.repository.OnBoardingRepository
import com.sy.onboarding_domain.usecase.IsFirstLaunchUseCase
import com.sy.onboarding_domain.usecase.SetFirstLaunchUseCase
import org.koin.dsl.module

val onBoardingModule = module {
    single<OnBoardingRepository> { OnBoardingRepositoryImpl(get()) }
    factory { IsFirstLaunchUseCase(get()) }
    factory { SetFirstLaunchUseCase(get()) }
}