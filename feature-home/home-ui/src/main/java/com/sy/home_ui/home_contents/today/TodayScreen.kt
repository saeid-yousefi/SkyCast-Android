package com.sy.home_ui.home_contents.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.sy.common_domain.model.OutCome
import com.sy.common_ui.ext.toDrawableId
import com.sy.common_ui.theme.LocalDimens

@Composable
fun TodayScreen(viewState: TodayState) {
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
        if (viewState.currentWeatherResult is OutCome.Success) {
            Image(
                painter = painterResource(id = viewState.currentWeatherResult.data.weather.first().weatherType.toDrawableId()),
                contentDescription = ""
            )
        }
    }
}