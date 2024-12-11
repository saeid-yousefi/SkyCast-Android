package com.sy.common_domain.exception

data class ServerException(val errorMessage: String, val errorCode: Int) : Exception()
class RequestTimeOutException : Exception()