package com.sy.home_ui.home_contents.today

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import com.sy.common_domain.model.weather.WeatherInfo
import com.sy.common_ui.composables.DashedLine
import com.sy.common_ui.composables.MultiStyleText
import com.sy.common_ui.ext.fullTimeToHourMinute
import com.sy.common_ui.ext.toCentigrade
import com.sy.common_ui.ext.toDrawableId
import com.sy.common_ui.theme.AppYellow
import com.sy.common_ui.theme.BlueGray
import com.sy.common_ui.theme.BlueGrayDark
import com.sy.common_ui.theme.CharcoalBlueDark
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
        isRefreshing = viewState.weatherInfoResult is OutCome.Loading || viewState.forecastResult is OutCome.Loading,
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
            viewState.weatherInfo?.let {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        with(it) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    contentScale = ContentScale.FillHeight,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(120.dp),
                                    painter = painterResource(id = weather.first().weatherType.toDrawableId()),
                                    contentDescription = ""
                                )
                                Spacer(modifier = Modifier.height(LocalDimens.current.paddingSmall))
                                main.feelsLike?.let {
                                    Box(
                                        modifier = Modifier.height(25.dp),
                                        contentAlignment = Alignment.BottomCenter
                                    ) {
                                        MultiStyleText(
                                            text1 = weather.first().main + " | " + stringResource(id = R.string.feels_like),
                                            style1 = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.tertiary),
                                            text2 = it.toCentigrade(context),
                                            style2 = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(120.dp),
                                ) {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = main.temp.toInt().toString(),
                                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 80.sp)
                                            .copy(
                                                shadow = Shadow(
                                                    color = Color.Black.copy(0.2f),
                                                    blurRadius = 10f,
                                                    offset = Offset(-10f, 12f)
                                                )
                                            )
                                    )
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center,
                                        text = main.temp.toInt().toString(),
                                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 80.sp)
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
                                Spacer(modifier = Modifier.height(LocalDimens.current.paddingSmall))
                                Box(
                                    modifier = Modifier.height(25.dp),
                                    contentAlignment = Alignment.BottomCenter
                                ) {
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
            }
            viewState.weatherInfo?.let {
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
                item {
                    DescribedClickableRow(
                        title = stringResource(id = R.string.forecast_title),
                        desc = stringResource(id = R.string.forecast_desc)
                    ) {

                    }
                }
            }
            println(viewState.forecast)
            viewState.forecast?.let { forecast ->
                items(forecast.take(9)) {
                    TodayForecast(weatherInfo = it)
                }
            }
        }
    }
}

@Composable
fun DescribedClickableRow(title: String, desc: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.large.copy(all = CornerSize(48.dp)))
            .clickable { onClick() }
            .background(color = CharcoalBlueDark)
            .padding(
                top = LocalDimens.current.paddingSmall,
                bottom = LocalDimens.current.paddingSmall,
                start = LocalDimens.current.paddingLarge,
                end = LocalDimens.current.paddingSmall
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(LocalDimens.current.paddingSmall))
            Text(
                text = desc,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .background(color = MaterialTheme.colorScheme.background)
                .clickable { onClick() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                modifier = Modifier
                    .rotate(-90f)
                    .size(12.dp),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun TodayForecast(weatherInfo: WeatherInfo) {
    val context = LocalContext.current
    with(weatherInfo) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.large)
                .clickable { }
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(CharcoalBlueDark, CharcoalBlueDark.copy(alpha = 0.7f))
                    )
                )
                .padding(LocalDimens.current.paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.paddingSmall)
        ) {
            Text(
                text = timeText?.fullTimeToHourMinute() ?: "",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall
            )
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = weather.first().weatherType.toDrawableId()),
                contentDescription = "",
            )
            Column(
                modifier = Modifier.weight(4f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = main.temp.toCentigrade(context, false),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = weather.first().description,
                    color = AppYellow,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                IconItem(
                    iconId = R.drawable.img_humidity,
                    textValue = main.humidity.toString() + "%"
                )
                IconItem(
                    iconId = R.drawable.img_wind,
                    textValue = wind.speed.toString()
                )
            }
        }
    }
}

@Composable
fun IconItem(modifier: Modifier = Modifier, iconId: Int, textId: Int? = null, textValue: String) {
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
        textId?.let {
            Text(
                text = stringResource(id = textId),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(2.dp))
        }
        Text(
            text = textValue,
            style = if (textId != null) MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}