package com.sy.skycast.di

import com.sy.onboarding_ui.OnBoardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onBoardingModule = module {
    viewModelOf(::OnBoardingViewModel)
}