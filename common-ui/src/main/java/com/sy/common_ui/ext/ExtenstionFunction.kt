package com.sy.common_ui.ext

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavController

fun NavController.popAndNavigate(route: String) {
    popBackStack()
    navigate(route)
}

fun TextFieldState.textAsFlow() = snapshotFlow { text }