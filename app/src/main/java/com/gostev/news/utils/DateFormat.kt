package com.gostev.news.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFormat {
    fun stringDateToString(stringDate: String?): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        return try {
            val date: Date = format.parse(stringDate?.trimEnd('.') ?: "") as Date
            val sFormat = SimpleDateFormat("dd/MM/yy", Locale.ENGLISH)
            sFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            ""
        }
    }
}