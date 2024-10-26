package com.sy.splash_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sy.common_ui.theme.Grey
import com.sy.common_ui.theme.GreyDark
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = koinViewModel()
    SplashScreen(navController = navController, viewModel = viewModel)
}

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel) {
    SplashScreen(viewState = viewModel.state.collectAsState().value) { action ->
        when (action) {
            else -> {}
        }
    }
}

@Composable
fun SplashScreen(viewState: SplashState, actionRunner: (SplashAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Grey, GreyDark),
                    start = androidx.compose.ui.geometry.Offset(
                        Float.POSITIVE_INFINITY,
                        0f
                    ),
                    end = androidx.compose.ui.geometry.Offset(
                        0f,
                        Float.POSITIVE_INFINITY
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        Image(
            painter = painterResource(id = R.drawable.weather_logo),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(0.5f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(id = R.string.application),
            color = Color.Black,
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp)
        )
    }
}