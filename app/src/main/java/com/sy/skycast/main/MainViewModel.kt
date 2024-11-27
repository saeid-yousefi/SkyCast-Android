package com.sy.skycast.main

import androidx.lifecycle.viewModelScope
import com.sy.common_ui.base.BaseViewModel
import com.sy.common_ui.message_manager.MessageBroker
import kotlinx.coroutines.launch

class MainViewModel(private val messageBroker: MessageBroker) :
    BaseViewModel<MainState, MainEffect, MainAction>() {

    init {
        viewModelScope.launch {
            val message = messageBroker.getMessageChannel().receive()
            setEffect(MainEffect.ShowSnackBarWithAction(message = message))
        }
    }

    override fun createInitialState(): MainState {
        return MainState()
    }

    override fun submitAction(action: MainAction) {
        when (action) {
            else -> {}
        }
    }
}