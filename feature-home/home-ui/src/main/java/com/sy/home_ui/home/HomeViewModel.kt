@file:OptIn(FlowPreview::class)

package com.sy.home_ui.home

import androidx.lifecycle.viewModelScope
import com.sy.common_domain.model.GeoName
import com.sy.common_domain.model.OutCome
import com.sy.common_domain.model.weather.CurrentWeather
import com.sy.common_ui.base.BaseViewModel
import com.sy.common_ui.ext.textAsFlow
import com.sy.common_ui.message_manager.Message
import com.sy.common_ui.message_manager.MessageBody
import com.sy.common_ui.message_manager.MessageBroker
import com.sy.home_domain.usecase.GetCurrentDateUseCase
import com.sy.home_domain.usecase.GetCurrentWeatherDataUseCase
import com.sy.home_domain.usecase.ObserveCityUseCase
import com.sy.home_domain.usecase.SaveCityUseCase
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeAction.ChangeCityBottomSheetVisibility
import com.sy.home_ui.home.HomeAction.SaveCity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val messageBroker: MessageBroker,
    private val saveCityUseCase: SaveCityUseCase,
    private val searchCityUseCase: SearchCityUseCase,
    private val observeCityUseCase: ObserveCityUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase
) :
    BaseViewModel<HomeState, HomeEffect, HomeAction>() {

    companion object {
        private const val DEBOUNCE_INTERVAL = 300L
    }

    private var searchJob: Job? = null
    val homeTextFields = HomeTextFields()

    init {
        observeHomeTextFields()
        observeSelectedCity()
        getCurrentDate()
    }

    override fun createInitialState() = HomeState()

    override fun submitAction(action: HomeAction) {
        when (action) {
            is ChangeCityBottomSheetVisibility -> handleCityBottomSheetVisibility(action.isVisible)
            is SaveCity -> saveCity(action.geoName)
            is HomeAction.GetCurrentWeather -> {
                viewModelScope.launch {
                    getCurrentWeatherData(action.cityName)
                }
            }

            else -> {}
        }
    }


    private fun handleCityBottomSheetVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            setState { copy(isCityBottomSheetVisible = isVisible) }
        }
    }

    private fun saveCity(geoName: GeoName) {
        viewModelScope.launch {
            saveCityUseCase(geoName)
        }
    }

    private fun observeHomeTextFields() {
        viewModelScope.launch {
            homeTextFields.searchCityTextFieldState
                .textAsFlow()
                .debounce(DEBOUNCE_INTERVAL)
                .filter { it.length >= 2 }
                .distinctUntilChanged()
                .collect { query ->
                    searchCity(query.toString())
                }
        }
    }

    private fun observeSelectedCity() {
        viewModelScope.launch {
            observeCityUseCase(Unit).collect { geoName ->
                setState { copy(geoName = geoName) }
                geoName?.name?.let {
                    getCurrentWeatherData(it)
                }
            }
        }
    }

    private suspend fun searchCity(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            searchCityUseCase(query).collect {
                setState { copy(citiesResult = it) }
            }
        }
    }

    private fun getCurrentDate() {
        viewModelScope.launch {
            getCurrentDateUseCase(Unit).let {
                val todayState = currentState.todayState.copy(todayDate = it)
                setState { copy(todayState = todayState) }
            }
        }
    }

    private suspend fun getCurrentWeatherData(cityName: String) {
        getCurrentWeatherDataUseCase(cityName).collect {
            var currentWeather: CurrentWeather? = currentState.todayState.currentWeather
            if (it is OutCome.Success) {
                currentWeather = it.data
            }
            if (it is OutCome.Failure) {
                messageBroker.sendMessage(
                    Message(
                        messageBody = MessageBody(throwable = it.throwable),
                        action = { submitAction(HomeAction.GetCurrentWeather(cityName)) })
                )
            }
            val todayState = currentState.todayState.copy(
                currentWeatherResult = it,
                currentWeather = currentWeather
            )
            setState { copy(todayState = todayState) }
        }
    }
}