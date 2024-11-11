package com.sy.home_ui.home

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeAction.ChangeCityBottomSheetVisibility
import kotlinx.coroutines.launch

class HomeViewModel(private val searchCityUseCase: SearchCityUseCase) :
    BaseViewModel<HomeState, HomeEffect, HomeAction>() {
    override fun createInitialState(): HomeState {
        return HomeState()
    }

    override fun updateTextInput(inputId: Int, text: String?) {
        viewModelScope.launch {
            when (inputId) {
                CITY_INPUT -> {
                    setState { copy(cityInput = cityInput.copy(text = text)) }
                }
            }
        }
    }

    override fun submitAction(action: HomeAction) {
        viewModelScope.launch {
            when (action) {
                is ChangeCityBottomSheetVisibility -> {
                    setState { copy(isCityBottomSheetVisible = action.isVisible) }
                }

                is HomeAction.UpdateTextInput -> {
                    updateTextInput(inputId = action.id, text = action.text)
                }
            }
        }
    }

    private suspend fun searchCity() {
        searchCityUseCase(currentState.cityInput.text ?: "").collect {
            setState { copy(citiesResult = it) }
        }
    }

    companion object {
        const val CITY_INPUT: Int = 1
    }
}