@file:OptIn(FlowPreview::class)

package com.sy.home_ui.home

import androidx.lifecycle.viewModelScope
import com.sy.common_domain.model.GeoName
import com.sy.common_domain.model.OutCome
import com.sy.common_domain.model.weather.WeatherInfo
import com.sy.common_ui.base.BaseViewModel
import com.sy.common_ui.ext.textAsFlow
import com.sy.common_ui.message_manager.Message
import com.sy.common_ui.message_manager.MessageBody
import com.sy.common_ui.message_manager.MessageBroker
import com.sy.home_domain.usecase.GetCurrentDateUseCase
import com.sy.home_domain.usecase.GetForeCastUseCase
import com.sy.home_domain.usecase.GetWeatherInfoUseCase
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
    private val getForeCastUseCase: GetForeCastUseCase,
    private val getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase,
) :
    BaseViewModel<HomeState, HomeEffect, HomeAction>() {

    companion object {
        private const val DEBOUNCE_INTERVAL = 300L
    }

    private var searchJob: Job? = null
    private var getCurrentWeatherJob: Job? = null
    private var getForecast: Job? = null
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
            is HomeAction.GetInitialData -> {
                getCurrentDate()
                getCurrentWeatherData(action.cityName)
                getForecast(action.cityName)
            }

            is SaveCity -> saveCity(action.geoName)
            is HomeAction.GetCurrentWeather -> getCurrentWeatherData(action.cityName)
            is HomeAction.GetForecast -> getForecast(action.cityName)
            is HomeAction.GetCurrentDate -> getCurrentDate()
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
                    submitAction(HomeAction.GetInitialData(it))
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

    private fun getCurrentWeatherData(cityName: String) {
        getCurrentWeatherJob?.cancel()
        getCurrentWeatherJob = viewModelScope.launch {
            getWeatherInfoUseCase(cityName).collect {
                var weatherInfo: WeatherInfo? = currentState.todayState.weatherInfo
                if (it is OutCome.Success) {
                    weatherInfo = it.data
                }
                if (it is OutCome.Failure) {
                    messageBroker.sendMessage(
                        Message(
                            messageBody = MessageBody(throwable = it.throwable),
                            action = { submitAction(HomeAction.GetCurrentWeather(cityName)) })
                    )
                }
                val todayState = currentState.todayState.copy(
                    weatherInfoResult = it,
                    weatherInfo = weatherInfo
                )
                setState { copy(todayState = todayState) }
            }
        }
    }

    private fun getForecast(cityName: String) {
        getForecast?.cancel()
        getForecast = viewModelScope.launch(Dispatchers.IO) {
            getForeCastUseCase(cityName).collect {
                var forecast = currentState.todayState.forecast
                if (it is OutCome.Success) {
                    forecast = it.data
                }
                if (it is OutCome.Failure) {
                    messageBroker.sendMessage(
                        Message(
                            messageBody = MessageBody(throwable = it.throwable),
                            action = { submitAction(HomeAction.GetForecast(cityName)) }
                        )
                    )
                }
                setState {
                    copy(
                        todayState = todayState.copy(
                            forecastResult = it,
                            forecast = forecast
                        )
                    )
                }
            }
        }
    }
}