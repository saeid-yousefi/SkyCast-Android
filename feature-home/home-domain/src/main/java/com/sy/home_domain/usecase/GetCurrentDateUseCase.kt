package com.sy.home_domain.usecase

import com.sy.common_domain.usecase.ValueUseCase
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class GetCurrentDateUseCase : ValueUseCase<Unit, String>() {
    override suspend fun execute(params: Unit): String {
        val currentMoment = Clock.System.now()
        val localDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

        val dayName = localDateTime.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() }
        val dayNumber = localDateTime.dayOfMonth
        val monthName =
            localDateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }

        return "$dayName, $dayNumber $monthName"
    }
}