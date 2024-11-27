package com.sy.skycast.di

import com.sy.common_ui.message_manager.MessageBroker
import com.sy.skycast.main.MainViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)
    singleOf(::MessageBroker)
}