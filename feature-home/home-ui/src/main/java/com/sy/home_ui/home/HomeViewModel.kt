@file:OptIn(FlowPreview::class)

package com.sy.home_ui.home

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import com.sy.common_ui.ext.textAsFlow
import com.sy.home_domain.usecase.ObserveCityUseCase
import com.sy.home_domain.usecase.SaveCityUseCase
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeAction.ChangeCityBottomSheetVisibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchCityUseCase: SearchCityUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val observeCityUseCase: ObserveCityUseCase,
) :
    BaseViewModel<HomeState, HomeEffect, HomeAction>() {

    private val debounceInterval = 300L
    private var searchJob: Job? = null
    val homeTextFields = HomeTextFields()

    init {
        observeHomeTextFields()
        observeSelectedCity()
    }

    override fun createInitialState(): HomeState {
        return HomeState()
    }

    override fun submitAction(action: HomeAction) {
        when (action) {
            is ChangeCityBottomSheetVisibility -> {
                viewModelScope.launch {
                    setState { copy(isCityBottomSheetVisible = action.isVisible) }
                }
            }

            is HomeAction.SaveCity -> {
                viewModelScope.launch {
                    saveCityUseCase(action.geoName)
                }
            }
        }
    }

    private fun observeHomeTextFields() {
        viewModelScope.launch {
            homeTextFields.searchCityTextFieldState
                .textAsFlow()
                .debounce(debounceInterval)
                .filter { it.length >= 2 }
                .distinctUntilChanged()
                .collect { query ->
                    searchCity(query.toString())
                }
        }
    }

    private fun observeSelectedCity() {
        viewModelScope.launch {
            observeCityUseCase(Unit).collect {
                setState { copy(geoName = it) }
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
}