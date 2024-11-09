package com.sy.common_domain.usecase

import com.sy.common_domain.model.OutCome
import kotlinx.coroutines.flow.flow

abstract class ResultUseCase<INPUT, OUTPUT> {
    suspend operator fun invoke(params: INPUT) = flow {
        emit(OutCome.Loading)
        try {
            emit(OutCome.Success(execute(params)))
        } catch (e: Exception) {
            emit(OutCome.Failure(e))
        }
    }

    abstract suspend fun execute(params: INPUT): OUTPUT
}