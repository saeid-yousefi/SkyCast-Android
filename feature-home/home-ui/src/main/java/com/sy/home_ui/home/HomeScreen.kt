@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.sy.home_ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.sy.common_domain.model.OutCome
import com.sy.common_ui.textfield.TextFieldInput
import com.sy.common_ui.theme.CharcoalBlue
import com.sy.common_ui.theme.LocalDimens
import com.sy.home_domain.model.GeoName
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
                    citiesResult = viewState.citiesResult,
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
    onDismissRequest: () -> Unit,
    citiesResult: OutCome<List<GeoName>>? = null
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
            TextField(
                value = cityInput.text ?: "",
                onValueChange = { updateTextInput(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.city),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                shape = MaterialTheme.shapes.large,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    cursorColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                )
            )
            Spacer(modifier = Modifier.height(LocalDimens.current.paddingMedium))
            if (citiesResult is OutCome.Success) {
                with(citiesResult) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(data) {
                            AssistChip(
                                shape = MaterialTheme.shapes.medium,
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = MaterialTheme.colorScheme.onBackground.copy(
                                        0.8f
                                    ),
                                    labelColor = MaterialTheme.colorScheme.secondary
                                ),
                                border = null,
                                onClick = {
                                    onDismissRequest()
                                },
                                label = {
                                    Text(
                                        text = "${it.name ?: stringResource(id = R.string.unknown)}, ${
                                            it.state ?: stringResource(
                                                id = R.string.unknown
                                            )
                                        }, ${it.countryName ?: stringResource(id = R.string.unknown)}",
                                        modifier = Modifier.padding(horizontal = LocalDimens.current.paddingMedium)
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}
