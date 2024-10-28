package com.sy.common_ui.ext

import androidx.navigation.NavController

fun NavController.popAndNavigate(route: String) {
    popBackStack()
    navigate(route)
}