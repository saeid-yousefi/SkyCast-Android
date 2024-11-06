package com.sy.common_domain.model

sealed interface Result<out T> {
    data class Success<out R>(val data: R) : Result<R>
    data class Failure(val throwable: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}
