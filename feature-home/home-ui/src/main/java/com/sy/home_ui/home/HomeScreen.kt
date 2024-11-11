@file:OptIn(ExperimentalMaterial3Api::class)

package com.sy.home_ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sy.common_ui.textfield.TextFieldInput
import com.sy.common_ui.theme.CharcoalBlue
import com.sy.common_ui.theme.LocalDimens
import com.sy.home_ui.R
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
    ) { action ->
        when (action) {

            else -> {
                viewModel.submitAction(action)
            }
        }
    }
}

@Composable
fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    viewState: HomeState,
    actionRunner: (HomeAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (viewState.geoName == null) stringResource(id = R.string.no_city_selected) else viewState.geoName.name.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
                actions = {
                    IconButton(
                        onClick = {
                            actionRunner(HomeAction.ChangeCityBottomSheetVisibility(true))
                        },
                        modifier = Modifier.padding(LocalDimens.current.paddingMedium),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_location),
                            contentDescription = ""
                        )
                    }
                }
            )
        }, snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (viewState.isCityBottomSheetVisible) {
                CityBottomSheet(
                    cityInput = viewState.cityInput,
                    updateTextInput = {
                        actionRunner(
                            HomeAction.UpdateTextInput(
                                id = HomeViewModel.CITY_INPUT,
                                text = it
                            )
                        )
                    },
                    onDismissRequest = {
                        actionRunner(HomeAction.ChangeCityBottomSheetVisibility(false))
                    })
            }
        }
    }
}

@Composable
fun CityBottomSheet(
    cityInput: TextFieldInput,
    updateTextInput: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = CharcoalBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalDimens.current.paddingMedium)
        ) {
            OutlinedTextField(
                value = cityInput.text ?: "",
                onValueChange = { updateTextInput(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                    unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                    focusedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                    cursorColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                )
            )

        }
    }
}
