package com.sy.common_ui.textfield

data class TextFieldInput(
    val text: String? = null,
    val hasError: Boolean = false,
    val errorMessage: Int? = null,
    val isRequired: Boolean = false,
    val textType: TextType = TextType.Text
)