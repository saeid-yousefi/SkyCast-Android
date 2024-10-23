package com.sy.common_domain.usecase

abstract class ActionUseCase<INPUT> {
    abstract suspend fun execute(params: INPUT)
}