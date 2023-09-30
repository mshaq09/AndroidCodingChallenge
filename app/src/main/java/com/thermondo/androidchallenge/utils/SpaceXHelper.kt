package com.thermondo.androidchallenge.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object SpaceXHelper {

    fun formatDate(date: String): String {
        val apiFormat = DateTimeFormatter.ofPattern( "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val parsedDate = LocalDate.parse(date , apiFormat)
        return "${parsedDate.dayOfMonth} - ${parsedDate.month.name} - ${parsedDate.year}"
    }
}
