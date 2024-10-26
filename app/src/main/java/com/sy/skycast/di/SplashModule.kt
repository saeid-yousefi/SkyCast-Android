package com.sy.skycast.di

import com.sy.splash_ui.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel() }
}