package com.sy.spash_domain.repositories

interface SplashRepository {
    suspend fun isFirstLaunch(): Boolean
    suspend fun setFirstLaunch(isFirstLaunch: Boolean = false)
}