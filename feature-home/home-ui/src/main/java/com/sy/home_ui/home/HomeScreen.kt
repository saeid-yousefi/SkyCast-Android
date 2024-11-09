package com.sy.home_ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, snackbarHostState: SnackbarHostState) {
    val viewModel = koinViewModel<HomeViewModel>()
    HomeScreen(
        navController = navController,
        snackbarHostState = snackbarHostState,
        viewModel = viewModel
    )
}

@Composable
fun HomeScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: HomeViewModel
) {
    HomeScreen(
        snackbarHostState = snackbarHostState,
        viewState = viewModel.state.collectAsState().value
    ) { action -> }
}

@Composable
fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    viewState: HomeState,
    actionRunner: (HomeAction) -> Unit
) {
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

        }
    }
}
