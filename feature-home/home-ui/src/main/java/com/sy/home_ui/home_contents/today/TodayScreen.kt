package com.sy.home_ui.home_contents.today

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sy.common_domain.model.OutCome
import com.sy.common_ui.composables.DashedLine
import com.sy.common_ui.composables.MultiStyleText
import com.sy.common_ui.ext.toCentigrade
import com.sy.common_ui.ext.toDrawableId
import com.sy.common_ui.theme.BlueGray
import com.sy.common_ui.theme.BlueGrayDark
import com.sy.common_ui.theme.LocalDimens
import com.sy.home_ui.R

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodayScreen(
    viewState: TodayState,
    onRefresh: () -> Unit
) {
    val context = LocalContext.current
    val refreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = viewState.currentWeatherResult is OutCome.Loading,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalDimens.current.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(LocalDimens.current.paddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier.background(
                        shape = MaterialTheme.shapes.large,
                        color = MaterialTheme.colorScheme.secondary,
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = LocalDimens.current.paddingMedium,
                            vertical = LocalDimens.current.paddingSmall
                        ),
                        text = viewState.todayDate,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    viewState.currentWeather?.let {
                        with(it) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(120.dp),
                                    painter = painterResource(id = weather.first().weatherType.toDrawableId()),
                                    contentDescription = ""
                                )
                                main.feelsLike?.let {
                                    MultiStyleText(
                                        text1 = weather.first().main + " | " + stringResource(id = R.string.feels_like),
                                        style1 = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.tertiary),
                                        text2 = it.toCentigrade(context),
                                        style2 = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Box(modifier = Modifier.size(120.dp)) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        text = main.temp.toCentigrade(context, false),
                                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 90.sp)
                                            .copy(
                                                shadow = Shadow(
                                                    color = Color.Black.copy(0.2f),
                                                    blurRadius = 10f,
                                                    offset = Offset(-10f, 12f)
                                                )
                                            )
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxSize(),
                                        text = main.temp.toCentigrade(context, false),
                                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 90.sp)
                                            .copy(
                                                brush = Brush.verticalGradient(
                                                    colors = listOf(
                                                        BlueGray,
                                                        BlueGrayDark
                                                    )
                                                )
                                            )
                                    )
                                }
                                Text(
                                    text = weather.first().description,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                            }
                        }
                    }
                }
            }
            viewState.currentWeather?.let {
                item {
                    DashedLine()
                }
                item {
                    Row(Modifier.fillMaxWidth()) {
                        IconItem(
                            iconId = R.drawable.img_humidity,
                            textId = R.string.humidity,
                            textValue = it.main.humidity.toString() + "%",
                            modifier = Modifier.weight(1f)
                        )
                        IconItem(
                            iconId = R.drawable.img_wind,
                            textId = R.string.wind,
                            textValue = it.wind.speed.toString(),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun IconItem(iconId: Int, textId: Int, textValue: String, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.then(modifier),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(id = textId),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = textValue,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}