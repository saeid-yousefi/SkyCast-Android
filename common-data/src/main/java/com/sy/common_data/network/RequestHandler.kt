package com.sy.common_data.network

import com.sy.common_domain.exception.ServerException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> bodyOrThrow(apiCall: () -> HttpResponse): T {
    try {
        val response = apiCall()
        if (response.status.value in 200..299) {
            return response.body<T>()
        } else {
            throw ServerException(response.status.description, response.status.value)
        }
    } catch (e: Exception) {
        throw e
    }
}