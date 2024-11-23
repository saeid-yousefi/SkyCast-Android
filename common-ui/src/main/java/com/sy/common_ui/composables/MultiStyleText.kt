package com.sy.common_ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun MultiStyleText(text1: String, style1: TextStyle, text2: String, style2: TextStyle) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = style1.color,
                fontSize = style1.fontSize,
                fontFamily = style1.fontFamily,
                fontWeight = style1.fontWeight
            )
        ) {
            append(text1)
            append(" ")
        }
        withStyle(
            style = SpanStyle(
                color = style2.color,
                fontSize = style2.fontSize,
                fontFamily = style2.fontFamily,
                fontWeight = style2.fontWeight
            )
        ) {
            append(text2)
        }
    })
}