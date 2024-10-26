package com.sy.common_domain.usecase

abstract class ActionUseCase<INPUT> {
    suspend operator fun invoke(params: INPUT) = execute(params)
    abstract suspend fun execute(params: INPUT)
}