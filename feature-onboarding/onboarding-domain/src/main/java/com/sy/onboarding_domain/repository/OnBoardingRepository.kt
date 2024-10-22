package com.sy.onboarding_domain.repository

interface OnBoardingRepository {
    suspend fun isFirstLaunch(): Boolean
    suspend fun setFirstLaunch(isFirstLaunch: Boolean = false)
}