package com.sy.common_ui.composables

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActionSnackBarHost(snackbarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackbarHostState) { data ->
        val visual = data.visuals as? ActionSnackBarVisuals
        Snackbar(
            modifier = Modifier
                .border(2.dp, MaterialTheme.colorScheme.secondary)
                .padding(12.dp),
            action = {
                visual?.actionLabel?.let {
                    TextButton(onClick = {
                        visual.action()
                    }) {
                        Text(text = it)
                    }
                }
            }
        ) {
            Text(text = data.visuals.message)
        }
    }
}

data class ActionSnackBarVisuals(
    val action: () -> Unit,
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = true,
    override val duration: SnackbarDuration,
) : SnackbarVisuals