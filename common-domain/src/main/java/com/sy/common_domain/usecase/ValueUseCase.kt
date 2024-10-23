package com.sy.common_domain.usecase

abstract class ValueUseCase<INPUT, OUTPUT> {
    abstract suspend fun execute(params: INPUT): OUTPUT
}