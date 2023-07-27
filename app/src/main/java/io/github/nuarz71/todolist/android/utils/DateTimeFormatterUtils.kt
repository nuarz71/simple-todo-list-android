package io.github.nuarz71.todolist.android.utils

import java.time.format.DateTimeFormatter

object DateTimeFormatterUtils {
    val OF_PATTERN_EEEE_DD_MMM_YYYY = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy")
    val ISO_OFFSET_DATE_TIME = DateTimeFormatter.ISO_OFFSET_DATE_TIME
}