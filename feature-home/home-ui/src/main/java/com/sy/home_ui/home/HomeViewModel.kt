package com.sy.home_ui.home

import com.sy.common_ui.base.BaseViewModel
import com.sy.home_domain.usecase.SearchCityUseCase

class HomeViewModel (private val searchCityUseCase: SearchCityUseCase) : BaseViewModel<HomeState, HomeEffect, HomeAction>() {
    override fun createInitialState(): HomeState {
        return HomeState()
    }

    override fun updateTextInput(inputId: Int, value: String) {
        TODO("Not yet implemented")
    }

    override fun submitAction(action: HomeAction) {
        TODO("Not yet implemented")
    }
}