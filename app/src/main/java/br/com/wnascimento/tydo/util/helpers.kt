package br.com.wnascimento.tydo.util

import android.text.format.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun formatForRelativeTime(date: Long): String {
    val now = System.currentTimeMillis()
    return DateUtils.getRelativeTimeSpanString(date, now, DateUtils.DAY_IN_MILLIS).toString()
}

fun formatForHours(date: Long): String {
    return DateFormat.getTimeInstance().format(date)
}


fun formatForCompletDateTime(dateTime: Long): String {

    val simpleFormat = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US)
    return simpleFormat.format(dateTime)
}
