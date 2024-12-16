package com.sy.common_ui.utils

import kotlinx.datetime.*

/**
 * Utility object for working with timestamps and extracting date/time components.
 */
object TimestampUtils {

    private val defaultTimeZone = TimeZone.currentSystemDefault()

    /**
     * Returns the day name (e.g., Sunday, Monday) for a given timestamp.
     *
     * @param timestamp The timestamp in milliseconds (Unix epoch time).
     * @param timeZone The time zone to use for conversion. Defaults to system's current time zone.
     * @return The full name of the day.
     */
    fun getDayNameFromTimestamp(timestamp: Long, timeZone: TimeZone = defaultTimeZone): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val dateTime = instant.toLocalDateTime(timeZone)
        return dateTime.date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercaseChar() }
    }

    /**
     * Returns the time in "HH:mm:ss" format for a given timestamp.
     *
     * @param timestamp The timestamp in milliseconds (Unix epoch time).
     * @param timeZone The time zone to use for conversion. Defaults to system's current time zone.
     * @return The formatted time string in "HH:mm:ss" format.
     */
    fun getTimeFromTimestamp(timestamp: Long, timeZone: TimeZone = defaultTimeZone): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val dateTime = instant.toLocalDateTime(timeZone)
        return dateTime.time.toString() // Format: HH:mm:ss
    }

    /**
     * Returns the time in "HH:mm" format for a given timestamp.
     *
     * @param timestamp The timestamp in milliseconds (Unix epoch time).
     * @param timeZone The time zone to use for conversion. Defaults to system's current time zone.
     * @return The formatted time string in "HH:mm" format.
     */
    fun getTimeHourMinuteFromTimestamp(timestamp: Long, timeZone: TimeZone = defaultTimeZone): String {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val dateTime = instant.toLocalDateTime(timeZone)
        return "%02d:%02d".format(dateTime.time.hour, dateTime.time.minute) // Format: HH:mm
    }
}
