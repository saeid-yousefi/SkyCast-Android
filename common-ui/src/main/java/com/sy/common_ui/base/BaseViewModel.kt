package com.sy.common_ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext


abstract class BaseViewModel<UiState, Effect, Action> :
    ViewModel() {

    private val initialState: UiState by lazy { createInitialState() }
    abstract fun createInitialState(): UiState

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<UiState> = _state

    val currentState: UiState
        get() = _state.value

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    protected suspend fun setEffect(effect: Effect) {
        _effect.send(effect)
    }

    abstract fun submitAction(action: Action)

    suspend fun setState(reduce: UiState.() -> UiState) {
        withContext(Dispatchers.Main) {
            val newState = currentState.reduce()
            _state.value = newState
        }
    }
}