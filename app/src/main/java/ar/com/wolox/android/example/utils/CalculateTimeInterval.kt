package ar.com.wolox.android.example.utils

import org.joda.time.DateTime
import org.joda.time.Days

class CalculateTimeInterval {

    companion object {

        fun calculate(dateText: String): Int {
            val date: DateTime = DateTime.parse(dateText)
            val currentDate: DateTime = DateTime.now()
            return Days.daysBetween(date, currentDate).days
        }
    }
}