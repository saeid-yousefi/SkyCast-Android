package com.sy.skycast

import android.app.Application
import com.sy.skycast.di.dataStoreModule
import com.sy.skycast.di.mainModule
import com.sy.skycast.di.onBoardingModule
import com.sy.skycast.di.splashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SkyCastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SkyCastApplication)
            modules(dataStoreModule, mainModule, onBoardingModule, splashModule)
        }
    }
}