package com.sy.common_domain.usecase

abstract class ValueUseCase<INPUT, OUTPUT> {
    suspend operator fun invoke(params: INPUT): OUTPUT = execute(params)
    abstract suspend fun execute(params: INPUT): OUTPUT
}