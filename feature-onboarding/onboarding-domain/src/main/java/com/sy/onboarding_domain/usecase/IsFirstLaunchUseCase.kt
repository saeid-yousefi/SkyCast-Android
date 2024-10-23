package com.sy.onboarding_domain.usecase

import com.sy.common_domain.usecase.ValueUseCase
import com.sy.onboarding_domain.repository.OnBoardingRepository

class IsFirstLaunchUseCase(private val repository: OnBoardingRepository) :
    ValueUseCase<Unit, Boolean>() {
    override suspend fun execute(params: Unit): Boolean {
        return repository.isFirstLaunch()
    }
}