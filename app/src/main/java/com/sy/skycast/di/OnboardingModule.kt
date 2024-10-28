package com.sy.skycast.di

import com.sy.onboarding_ui.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    viewModel { OnBoardingViewModel() }
}