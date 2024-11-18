package com.sy.common_ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sy.common_ui.theme.GraySlate
import com.sy.common_ui.theme.LocalDimens

@Composable
fun AppTab(modifier: Modifier = Modifier, text: String, selected: Boolean, onClick: () -> Unit) {
    Tab(selected = selected, onClick = onClick, modifier = Modifier.then(modifier)) {
        Text(
            text = text,
            modifier = Modifier.padding(LocalDimens.current.paddingMedium),
            color = if (selected) MaterialTheme.colorScheme.onBackground else GraySlate,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}