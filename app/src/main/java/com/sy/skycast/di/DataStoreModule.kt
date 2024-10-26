package com.sy.skycast.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore(name = "skycast_settings")

val dataStoreModule = module {
    single { provideDataStore(androidContext()) }
}

fun provideDataStore(context: Context) = context.dataStore
