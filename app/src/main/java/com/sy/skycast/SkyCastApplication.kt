package com.sy.skycast

import android.app.Application
import com.sy.skycast.di.dataStoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SkyCastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SkyCastApplication)
            modules(dataStoreModule)
        }
    }
}