package com.sy.onboarding_domain.usecase

import com.sy.common_domain.usecase.ActionUseCase
import com.sy.onboarding_domain.repository.OnBoardingRepository

class SetFirstLaunchUseCase(private val repository: OnBoardingRepository) :
    ActionUseCase<Boolean>() {
    override suspend fun execute(params: Boolean) {
        return repository.setFirstLaunch(params)
    }
}