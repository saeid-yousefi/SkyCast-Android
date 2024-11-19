@file:OptIn(ExperimentalMaterial3Api::class)

package com.sy.common_ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.sy.common_ui.theme.LocalDimens

@Composable
fun AppCenterAlignedTopAppBar(title: String, actionIconId: Int, onActionClick: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent),
        actions = {
            IconButton(
                onClick = onActionClick,
                modifier = Modifier.padding(LocalDimens.current.paddingMedium),
            ) {
                Icon(
                    painter = painterResource(id = actionIconId),
                    contentDescription = ""
                )
            }
        }
    )
}