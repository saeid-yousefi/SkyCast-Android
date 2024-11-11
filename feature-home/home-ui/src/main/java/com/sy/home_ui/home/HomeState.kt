package com.sy.home_ui.home

import com.sy.common_ui.textfield.TextFieldInput
import com.sy.home_domain.model.GeoName

data class HomeState(
    val geoName: GeoName? = null,
    val isCityBottomSheetVisible: Boolean = false,
    val cityInput: TextFieldInput = TextFieldInput()
)
