package com.sy.skycast.main

import com.sy.common_ui.message_manager.Message

sealed class MainEffect {
    data class ShowSnackBarWithAction(val message: Message) : MainEffect()
}