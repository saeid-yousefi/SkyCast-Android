package com.sy.spash_domain.usecases

import com.sy.common_domain.usecase.ValueUseCase
import com.sy.spash_domain.repositories.SplashRepository

class IsFirstLaunchUseCase(private val repository: SplashRepository) :
    ValueUseCase<Unit, Boolean>() {

    override suspend fun execute(params: Unit): Boolean {
        return repository.isFirstLaunch()
    }
}