package com.sy.spash_data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.sy.spash_domain.repositories.SplashRepository
import kotlinx.coroutines.flow.first

class SplashRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    SplashRepository {
    companion object {
        private val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")
    }

    override suspend fun isFirstLaunch(): Boolean {
        val preferences = dataStore.data.first()
        return preferences[IS_FIRST_LAUNCH_KEY] ?: true
    }

    override suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        dataStore.edit {
            it[IS_FIRST_LAUNCH_KEY] = isFirstLaunch
        }
    }
}