package com.sy.common_domain.model

sealed interface OutCome<out T> {
    data class Success<out R>(val data: R) : OutCome<R>
    data class Failure(val throwable: Throwable) : OutCome<Nothing>
    data object Loading : OutCome<Nothing>
}
