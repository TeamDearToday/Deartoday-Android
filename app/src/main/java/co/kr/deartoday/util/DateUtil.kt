package co.kr.deartoday.util

import java.text.SimpleDateFormat
import java.util.*

fun getTodayString(): String {
    val todayFormat = SimpleDateFormat("yyyy.MM.dd")
    return todayFormat.format(Date())
}