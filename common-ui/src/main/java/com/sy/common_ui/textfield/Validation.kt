package com.sy.common_ui.textfield

import com.sy.common_ui.R

object Validation {
    fun validate(input: TextFieldInput): TextFieldInput {
        if (input.isRequired && input.text.isNullOrBlank()) {
            return input.copy(
                hasError = true,
                errorMessage = R.string.validation_field_is_empty
            )
        }
        if (!input.isRequired && input.text.isNullOrBlank()) {
            return input.copy(hasError = false, errorMessage = null)
        }
        return when (input.textType) {
            TextType.Text -> input.copy(hasError = false, errorMessage = null)
        }
    }
}