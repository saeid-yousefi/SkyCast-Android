package com.sy.common_ui.ext

import android.content.Context
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

fun Float.toCentigrade(context: Context, showUnit: Boolean = true): String {
    val unit =
        if (showUnit) context.getString(R.string.weather_c_unit) else context.getString(R.string.weather_unit)
    return "${this.toInt()}$unit"
}

fun String.fullTimeToHourMinute(): String {
    val regex = Regex("\\d{2}:\\d{2}")
    return regex.find(this)?.value.toString()
}
