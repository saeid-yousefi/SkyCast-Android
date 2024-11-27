package com.sy.skycast.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.sy.common_ui.composables.ActionSnackBarHost
import com.sy.common_ui.composables.ActionSnackBarVisuals
import com.sy.skycast.navigation.AppNavHost
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = koinViewModel<MainViewModel>()
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collect {
            when (it) {
                is MainEffect.ShowSnackBarWithAction -> {
                    snackbarHostState.showSnackbar(
                        ActionSnackBarVisuals(
                            action = it.message.action,
                            message = it.message.messageBody.getCorrectMessage(context),
                            actionLabel = it.message.actionButton,
                            withDismissAction = true,
                            duration = SnackbarDuration.Short
                        )
                    )
                }
            }
        }
    }
    Scaffold(
        snackbarHost = {
            ActionSnackBarHost(snackbarHostState = snackbarHostState)
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AppNavHost(navController)
        }
    }
}