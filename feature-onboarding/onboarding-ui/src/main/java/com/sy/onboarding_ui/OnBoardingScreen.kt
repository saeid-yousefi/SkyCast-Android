@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalAnimationApi::class,
    ExperimentalAnimationApi::class
)

package com.sy.onboarding_ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
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
import com.sy.common_ui.ext.popAndNavigate
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
    val viewState = viewModel.state.collectAsState().value
    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is OnBoardingEffect.NavigateTo -> navController.popAndNavigate(effect.route)
            }
        }
    }
    OnBoardingScreen(viewState = viewState) { action ->
        when (action) {
            is OnBoardingAction.NavigateTo -> {
                navController.popAndNavigate(action.route)
            }

            else -> {
                viewModel.submitAction(action)
            }
        }
    }
    BackHandler {
        if (viewState.currentPageIndex == 0) {
            navController.popBackStack()
        } else {
            viewModel.submitAction(OnBoardingAction.PreviousOnBoard)
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
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
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AnimatedContent(
                            targetState = viewState.currentPageIndex,
                            transitionSpec = {
                                fadeIn() + slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) with fadeOut() + slideOutHorizontally(
                                    targetOffsetX = { fullWidth -> -fullWidth })
                            }, label = ""
                        ) { index ->
                            Image(
                                painter = painterResource(id = OnBoardingPages[index].imageId),
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentScale = ContentScale.FillHeight
                            )
                        }
                        Stepper(
                            modifier = Modifier.fillMaxHeight(0.2f),
                            currentStep = viewState.currentPageIndex
                        )
                    }
                    Spacer(modifier = Modifier.height(LocalDimens.current.paddingLarge))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = LocalDimens.current.paddingMedium),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(2f))
                    AnimatedContent(
                        targetState = viewState.currentPageIndex, transitionSpec = {
                            fadeIn() + slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) with fadeOut() + slideOutHorizontally(
                                targetOffsetX = { fullWidth -> -fullWidth })
                        }, label = ""
                    ) { index ->
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = stringResource(id = OnBoardingPages[index].titleId),
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                minLines = 2,
                                maxLines = 2
                            )
                            Spacer(modifier = Modifier.height(LocalDimens.current.paddingLarge))
                            Text(
                                text = stringResource(id = OnBoardingPages[index].descriptionId),
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                minLines = 2,
                                maxLines = 2
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(80.dp)) {
                        DrawCircle(((viewState.currentPageIndex + 1).toFloat() / OnBoardingPages.size.toFloat()))

                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(brush = MainGradientBrush)
                                .clickable {
                                    actionRunner(OnBoardingAction.NextOnBoard)
                                }, contentAlignment = Alignment.Center
                        ) {
                            AnimatedContent(
                                targetState = viewState.currentPageIndex,
                                transitionSpec = { fadeIn() with fadeOut() },
                                label = ""
                            ) { index ->
                                val imageId =
                                    if (index < 3) R.drawable.ic_next else R.drawable.ic_check
                                Icon(
                                    painter = painterResource(id = imageId),
                                    tint = Color.White,
                                    contentDescription = ""
                                )
                            }
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
            x = (screenWidth.div(2)).dp, y = screenHeight.dp
        ), onDraw = {
        drawCircle(color = Color.White, radius = screenHeight.div(2).dp.toPx())
    })
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

@Composable
private fun Stepper(
    modifier: Modifier = Modifier,
    currentStep: Int = 0,
    stepCounts: Int = OnBoardingPages.size - 1,
) {
    Row(
        modifier = Modifier.then(modifier),
        horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.paddingSmall),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0..stepCounts) {
            val isIndex = currentStep == i
            val scaleState = animateFloatAsState(targetValue = if (isIndex) 2f else 1f, label = "")
            Canvas(
                modifier = Modifier
                    .height(10.dp)
                    .width(if (isIndex) 24.dp else 10.dp)
                    .scale(scaleX = scaleState.value, scaleY = 1f)
                    .padding(horizontal = if (isIndex) 6.dp else 0.dp)
            ) {
                drawRoundRect(
                    color = if (isIndex) Color.Black else Color.White,
                    cornerRadius = if (isIndex) CornerRadius(10f, 10f) else CornerRadius(20f, 20f)
                )
            }
        }

    }
}