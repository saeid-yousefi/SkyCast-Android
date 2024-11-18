package com.sy.home_data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sy.home_data.data_source.remote.data.HomeRemoteDataSource
import com.sy.home_domain.model.GeoName
import com.sy.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HomeRepositoryImpl(
    private val remoteDataSource: HomeRemoteDataSource,
    private val dataStore: DataStore<Preferences>
) : HomeRepository {

    companion object {
        private val CITY_KEY = stringPreferencesKey("city_key")
    }

    override suspend fun searchCity(cityName: String): List<GeoName> {
        return remoteDataSource.searchCity(cityName).map { it.toGeoName() }
    }

    override suspend fun observeCity(): Flow<GeoName?> {
        return dataStore.data.map { preferences ->
            preferences[CITY_KEY]?.let {
                Json.decodeFromString<GeoName>(it)
            }
        }
    }

    override suspend fun saveCity(geoName: GeoName) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[CITY_KEY] = Json.encodeToString<GeoName>(geoName)
        }
    }
}