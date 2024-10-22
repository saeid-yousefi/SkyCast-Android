package com.sy.onboarding_data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.sy.onboarding_domain.repository.OnBoardingRepository
import kotlinx.coroutines.flow.first

class OnBoardingRepositoryImpl(private val dataStore: DataStore<Preferences>) :
    OnBoardingRepository {
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