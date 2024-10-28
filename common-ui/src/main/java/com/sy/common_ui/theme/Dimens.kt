package com.sy.common_ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(val paddingSmall: Dp, val paddingMedium: Dp, val paddingLarge: Dp)

internal val NormalDimens = Dimens(
    paddingSmall = 8.dp,
    paddingMedium = 16.dp,
    paddingLarge = 24.dp
)

val LocalDimens =
    compositionLocalOf { NormalDimens }
