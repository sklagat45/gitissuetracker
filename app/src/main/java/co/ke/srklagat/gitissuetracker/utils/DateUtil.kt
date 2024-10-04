package co.ke.srklagat.gitissuetracker.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun DateUtil(dateString: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(dateString)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")
        zonedDateTime.format(formatter)
    } catch (e: Exception) {
        "Invalid date"
    }
}
