package com.sy.common_ui.message_manager

import kotlinx.coroutines.channels.Channel

class MessageBroker {
    private val messageChannel = Channel<Message>()

    suspend fun sendMessage(message: Message) {
        messageChannel.send(message)
    }

    fun getMessageChannel(): Channel<Message> = messageChannel
}