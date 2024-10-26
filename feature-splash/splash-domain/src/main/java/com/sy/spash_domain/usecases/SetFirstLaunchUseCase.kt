package com.sy.spash_domain.usecases

import com.sy.common_domain.usecase.ActionUseCase
import com.sy.spash_domain.repositories.SplashRepository

class SetFirstLaunchUseCase(private val repository: SplashRepository) :
    ActionUseCase<Boolean>() {
    override suspend fun execute(params: Boolean) {
        return repository.setFirstLaunch(params)
    }
}