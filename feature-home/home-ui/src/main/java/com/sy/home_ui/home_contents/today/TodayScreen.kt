package com.sy.home_ui.home_contents.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import com.sy.common_domain.model.OutCome
import com.sy.common_ui.composables.MultiStyleText
import com.sy.common_ui.ext.toCentigrade
import com.sy.common_ui.ext.toDrawableId
import com.sy.common_ui.theme.BlueGray
import com.sy.common_ui.theme.BlueGrayDark
import com.sy.common_ui.theme.LocalDimens
import com.sy.home_ui.R

@Composable
fun TodayScreen(viewState: TodayState) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalDimens.current.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.paddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (viewState.currentWeatherResult is OutCome.Loading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
            }
            if (viewState.currentWeatherResult is OutCome.Success) {
                with(viewState.currentWeatherResult.data) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.paddingMedium)
                    ) {
                        Image(
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .aspectRatio(1.5f),
                            painter = painterResource(id = weather.first().weatherType.toDrawableId()),
                            contentDescription = ""
                        )
                        main.feelsLike?.let {
                            MultiStyleText(
                                text1 = stringResource(id = R.string.feels_like),
                                style1 = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.tertiary),
                                text2 = it.toCentigrade(context),
                                style2 = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground)
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.paddingMedium)
                    ) {
                        Box {
                            Text(
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

                    }
                }
            }
        }
    }
}