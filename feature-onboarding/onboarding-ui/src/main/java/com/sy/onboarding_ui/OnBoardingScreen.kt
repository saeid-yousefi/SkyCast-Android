@file:OptIn(ExperimentalMaterial3Api::class)

package com.sy.onboarding_ui

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sy.common_ui.composables.MainGradientBrush
import com.sy.common_ui.theme.LocalDimens
import com.sy.common_ui.theme.PinkDark
import com.sy.common_ui.theme.PinkLight
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OnBoardingScreen(navController: NavController) {
    val viewModel: OnBoardingViewModel = koinViewModel()
    OnBoardingScreen(navController = navController, viewModel = viewModel)
}

@Composable
fun OnBoardingScreen(navController: NavController, viewModel: OnBoardingViewModel) {

    OnBoardingScreen(viewState = viewModel.state.collectAsState().value) { action ->
        when (action) {

            else -> {}
        }
    }
}

@Composable
fun OnBoardingScreen(viewState: OnBoardingState, actionRunner: (OnBoardingAction) -> Unit) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = MainGradientBrush)
                .padding(innerPadding)
        ) {
            DrawArc()
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(LocalDimens.current.paddingMedium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.fillMaxWidth()) {
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = stringResource(id = R.string.skip), color = Color.White)
                        }
                    }
                    Image(
                        painter = painterResource(id = R.drawable.image_one),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = viewState.onBoardingPage.titleId),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(LocalDimens.current.paddingLarge))
                    Text(
                        text = stringResource(id = viewState.onBoardingPage.descriptionId),
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(80.dp)) {
                        DrawCircle(((viewState.currentPageIndex + 1).toFloat() / OnBoardingPages.size.toFloat()))

                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(brush = MainGradientBrush)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_next),
                                tint = Color.White,
                                contentDescription = ""
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(0.5f))
                }
            }
        }
    }
}

@Composable
private fun DrawArc() {
    val configuration = LocalConfiguration.current
    val screenHeight = remember { configuration.screenHeightDp }
    val screenWidth = remember { configuration.screenWidthDp }
    Canvas(modifier = Modifier
        .size(0.dp)
        .offset(
            x = (screenWidth.div(2)).dp,
            y = screenHeight.dp
        ),
        onDraw = {
            drawCircle(color = Color.White, radius = screenHeight.div(2).dp.toPx())
        }
    )
}

@Composable
private fun DrawCircle(progress: Float = 0.25f) {

    val animatedProgress by animateFloatAsState(targetValue = progress, label = "")

    Canvas(modifier = Modifier.size(100.dp)) {
        val diameter = size.minDimension
        val sweepAngle = 360f * animatedProgress
        drawArc(
            brush = Brush.verticalGradient(listOf(PinkLight, PinkDark)),
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(width = 12f, cap = StrokeCap.Round),
            size = Size(diameter, diameter)
        )
    }
}