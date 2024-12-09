package com.sy.common_ui.message_manager

import android.content.Context
import com.sy.common_domain.exception.ServerException
import com.sy.common_ui.R
import java.net.ConnectException
import java.net.SocketTimeoutException

data class Message(
    val messageBody: MessageBody,
    val throwable: Throwable? = null,
    val actionButton: String? = null,
    val action: () -> Unit = {}
)

data class MessageBody(val text: String? = null, val throwable: Throwable? = null) {

    fun getCorrectMessage(context: Context? = null): String {
        text?.let {
            return text
        }
        return when (throwable) {
            is ServerException -> return throwable.errorMessage
            is SocketTimeoutException -> context?.getString(R.string.socket_timeout_error)!!
            is ConnectException -> context?.getString(R.string.connection_failed_error)!!
            else -> context?.getString(R.string.connection_failed_error)!!
        }
    }
}