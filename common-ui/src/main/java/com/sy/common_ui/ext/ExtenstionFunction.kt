package com.sy.common_ui.ext

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.snapshotFlow
import androidx.navigation.NavController
import com.sy.common_domain.model.weather.WeatherType
import com.sy.common_ui.R

fun NavController.popAndNavigate(route: String) {
    popBackStack()
    navigate(route)
}

fun TextFieldState.textAsFlow() = snapshotFlow { text }

fun WeatherType.toDrawableId(): Int {
    return when (this) {
        WeatherType.SUNNY -> R.drawable.ic_sunny
        WeatherType.CLOUDY -> R.drawable.ic_cloudy
        WeatherType.RAINY -> R.drawable.ic_rainy
        WeatherType.SNOWY -> R.drawable.ic_snowy
        WeatherType.WINDY -> R.drawable.ic_windy
        WeatherType.STORMY -> R.drawable.ic_stormy
        WeatherType.FOGGY -> R.drawable.ic_foggy
        WeatherType.UNKNOWN -> R.drawable.ic_sunny
    }
}