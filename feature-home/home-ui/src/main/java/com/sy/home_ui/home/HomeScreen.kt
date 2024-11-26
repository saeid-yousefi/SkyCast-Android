@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.sy.home_ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sy.common_domain.model.GeoName
import com.sy.common_domain.model.OutCome
import com.sy.common_ui.composables.AppCenterAlignedTopAppBar
import com.sy.common_ui.composables.AppTab
import com.sy.common_ui.theme.CharcoalBlue
import com.sy.common_ui.theme.LocalDimens
import com.sy.common_ui.theme.PinkRose
import com.sy.home_ui.R
import com.sy.home_ui.home_contents.today.TodayScreen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController, snackbarHostState: SnackbarHostState) {
    val viewModel = koinViewModel<HomeViewModel>()

    HomeScreen(
        navController = navController,
        snackbarHostState = snackbarHostState,
        homeTextFields = viewModel.homeTextFields,
        viewModel = viewModel
    )
}

@Composable
fun HomeScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    homeTextFields: HomeTextFields,
    viewModel: HomeViewModel
) {
    val pagerState = rememberPagerState { HomeTabs.size }
    val scope = rememberCoroutineScope()

    HomeScreen(
        snackbarHostState = snackbarHostState,
        homeTextFields = homeTextFields,
        pagerState = pagerState,
        viewState = viewModel.state.collectAsState().value
    ) { action ->
        when (action) {
            is HomeAction.ChangePagerState -> {
                scope.launch {
                    pagerState.animateScrollToPage(action.index)
                }
            }

            else -> viewModel.submitAction(action)

        }
    }
}

@Composable
fun HomeScreen(
    snackbarHostState: SnackbarHostState,
    homeTextFields: HomeTextFields,
    pagerState: PagerState,
    viewState: HomeState,
    actionRunner: (HomeAction) -> Unit
) {
    Scaffold(
        topBar = {
            AppCenterAlignedTopAppBar(
                title = if (viewState.geoName == null) stringResource(id = R.string.no_city_selected) else viewState.geoName.name.toString(),
                actionIconId = R.drawable.ic_add_location,
                onActionClick = { actionRunner(HomeAction.ChangeCityBottomSheetVisibility(true)) })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (viewState.isCityBottomSheetVisible) {
                CityBottomSheet(
                    searchTextField = homeTextFields.searchCityTextFieldState,
                    citiesResult = viewState.citiesResult,
                    onConfirm = {
                        actionRunner(HomeAction.SaveCity(it))
                    },
                    onDismissRequest = {
                        actionRunner(HomeAction.ChangeCityBottomSheetVisibility(false))
                    })
            }
            if (viewState.geoName == null) {
                EmptyCity {
                    actionRunner(HomeAction.ChangeCityBottomSheetVisibility(true))
                }
            } else {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = Color.Transparent,
                    indicator = { tabPositions ->
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                .height(1.dp)
                                .padding(horizontal = LocalDimens.current.paddingMedium)
                                .background(MaterialTheme.colorScheme.onBackground)
                        )
                    }
                ) {
                    HomeTabs.forEachIndexed { index, textId ->
                        AppTab(
                            text = stringResource(id = textId),
                            selected = index == pagerState.currentPage,
                        ) {
                            actionRunner(HomeAction.ChangePagerState(index))
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.5f)
                )
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = false
                ) { page ->
                    when (page) {
                        0 -> TodayScreen(
                            viewState = viewState.todayState,
                            onRefresh = {
                                viewState.geoName.name?.let {
                                    actionRunner(HomeAction.GetCurrentWeather(it))
                                }
                            })

                        1 -> Text(text = "SALAM")
                    }
                }
            }
        }
    }
}

@Composable
fun EmptyCity(onButtonClicked: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.4f),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.img_location),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(LocalDimens.current.paddingMedium))
        Text(
            text = stringResource(id = R.string.no_city_selected_desc),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(LocalDimens.current.paddingMedium))

        TextButton(
            onClick = onButtonClicked,
            colors = ButtonDefaults.textButtonColors(
                containerColor = PinkRose
            )
        ) {
            Text(
                modifier = Modifier.padding(horizontal = LocalDimens.current.paddingMedium),
                text = stringResource(id = R.string.select_city),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun CityBottomSheet(
    searchTextField: TextFieldState,
    onConfirm: (GeoName) -> Unit,
    onDismissRequest: () -> Unit,
    citiesResult: OutCome<List<GeoName>>? = null
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
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
            BasicTextField(
                state = searchTextField,
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSecondary),
                lineLimits = TextFieldLineLimits.SingleLine,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
                decorator = { textFieldContent ->
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .background(
                                color = MaterialTheme.colorScheme.secondary.copy(0.5f),
                                shape = MaterialTheme.shapes.large
                            )
                            .padding(horizontal = LocalDimens.current.paddingMedium),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (searchTextField.text.isEmpty()) {
                            Text(
                                text = stringResource(id = R.string.city),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f)
                            )
                        }
                        textFieldContent()

                        this@Column.AnimatedVisibility(
                            visible = citiesResult is OutCome.Loading,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.onSecondary.copy(0.5f)
                                )
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(LocalDimens.current.paddingMedium))
            if (citiesResult is OutCome.Success) {
                with(citiesResult) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        items(data, key = { geoName -> geoName.geoNameId ?: 0 }) {
                            AssistChip(
                                shape = MaterialTheme.shapes.medium,
                                colors = AssistChipDefaults.assistChipColors(
                                    containerColor = MaterialTheme.colorScheme.onBackground
                                        .copy(0.8f),
                                    labelColor = MaterialTheme.colorScheme.secondary
                                ),
                                border = null,
                                onClick = {
                                    onConfirm(it)
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
