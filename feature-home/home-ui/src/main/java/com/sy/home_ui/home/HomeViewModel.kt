@file:OptIn(FlowPreview::class)

package com.sy.home_ui.home

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeAction.ChangeCityBottomSheetVisibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeViewModel(private val searchCityUseCase: SearchCityUseCase) :
    BaseViewModel<HomeState, HomeEffect, HomeAction>() {

    private val debounceInterval = 300L
    private var searchJob: Job? = null
    private val citySearchFlow = MutableStateFlow("")

    init {
        viewModelScope.launch {
            citySearchFlow
                .debounce(debounceInterval)
                .filter { it.length >= 2 }
                .distinctUntilChanged()
                .collect { query ->
                    searchCity(query)
                }
        }
    }

    override fun createInitialState(): HomeState {
        return HomeState()
    }

    override suspend fun updateTextInput(inputId: Int, text: String?) {
        when (inputId) {
            CITY_INPUT -> {
                setState { copy(cityInput = cityInput.copy(text = text)) }
                text?.let { citySearchFlow.emit(it) }
            }
        }
    }

    override fun submitAction(action: HomeAction) {
        when (action) {
            is ChangeCityBottomSheetVisibility -> {
                viewModelScope.launch {
                    setState { copy(isCityBottomSheetVisible = action.isVisible) }
                }
            }

            is HomeAction.UpdateTextInput -> {
                viewModelScope.launch(Dispatchers.IO) {
                    updateTextInput(inputId = action.id, text = action.text)
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

    companion object {
        const val CITY_INPUT: Int = 1
    }
}