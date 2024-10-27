package com.sy.onboarding_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OnBoardingScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        DrawSizeTest()
    }
}

@Composable
private fun DrawSizeTest() {
    Box(modifier = Modifier.fillMaxSize()) {
        val screenHeight = LocalConfiguration.current.screenHeightDp
        val screenWidth = LocalConfiguration.current.screenWidthDp
        println(LocalConfiguration.current.screenHeightDp)
        Canvas(modifier = Modifier
            .size(0.dp)
            .offset(
                x = (screenWidth.div(2)).dp,
                y = screenHeight.dp
            ),
            onDraw = {
                drawCircle(color = Color.DarkGray, radius = screenHeight.div(2).dp.toPx())
            }
        )
    }
}