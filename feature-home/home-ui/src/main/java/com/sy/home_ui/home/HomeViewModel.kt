package com.sy.home_ui.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sy.common_domain.model.OutCome
import com.sy.common_ui.base.BaseViewModel
import com.sy.home_domain.usecase.SearchCityUseCase
import com.sy.home_ui.home.HomeAction.ChangeCityBottomSheetVisibility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(private val searchCityUseCase: SearchCityUseCase) :
    BaseViewModel<HomeState, HomeEffect, HomeAction>() {

    private val timeInterval = 300
    private var lastTimeSearchCalled: Long = 0

    private var searchJob: Job = Job()

    override fun createInitialState(): HomeState {
        return HomeState()
    }

    override fun updateTextInput(inputId: Int, text: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            when (inputId) {
                CITY_INPUT -> {
                    setState { copy(cityInput = cityInput.copy(text = text)) }
                }
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
                updateTextInput(inputId = action.id, text = action.text)
            }

            HomeAction.SearchCity -> {
                viewModelScope.launch {
                    if (System.currentTimeMillis() - lastTimeSearchCalled >= timeInterval) {
                        lastTimeSearchCalled = System.currentTimeMillis()
                        searchJob = Job()
                        searchCity()
                    }
                }
            }
        }
    }


    private suspend fun searchCity() {
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            searchCityUseCase(currentState.cityInput.text ?: "").collect {
                if (it is OutCome.Success) {
                    Log.e("CITY NAME", currentState.cityInput.text ?: "")
                    Log.e("CITY LIST", it.data.toString())
                }
                setState { copy(citiesResult = it) }
            }
        }
    }

    companion object {
        const val CITY_INPUT: Int = 1
    }
}