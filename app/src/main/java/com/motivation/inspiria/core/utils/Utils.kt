package com.motivation.inspiria.core.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.temporal.ChronoUnit
import java.util.Calendar


fun getMotivationQuotesOfViewPager(): ArrayList<String> {
    return arrayListOf<String>().apply {
        add("\"The harder the struggle, the more glorious the triumph. Self-realization demands very great struggle.\"")  // Swami Sivananda
        add("\"Believe in yourself. Stay in your own lane. There’s only one you.\"")  // Queen Latifah
        add("\"You just can’t beat the person who never gives up.\"")  // Babe Ruth
        add("\"In order to succeed. We must first believe that we can.\"")  // Nikos Kazantzakis
        add("\"It is not the strength of the body that counts, but the strength of the spirit.\"")  // J.R.R. Tolkien
    }
}




fun getMonthName(month:Int):String{
    return when(month){
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> "January"
    }
}

fun getCurrentTime(): Pair<Int, String> {
    val calendar = Calendar.getInstance()
    val date = calendar.get(Calendar.DAY_OF_MONTH)
    val month = getMonthName(calendar.get(Calendar.MONTH))
    return Pair(date, month)
}

fun LocalDateTime.nextDayMorning(): LocalDateTime {
    val desiredTime = LocalTime.of(9, 0)  // Set time to 9 AM
    var nextDayAtMorning: LocalDateTime = this.toLocalDate().atTime(desiredTime)
    if (this.toLocalTime().isAfter(desiredTime)) {
        nextDayAtMorning = nextDayAtMorning.plus(1, ChronoUnit.DAYS)
    }
    return nextDayAtMorning.also { println("${it.dayOfMonth}/${it.month}/${it.year}") }
}

fun LocalDateTime.nextDayNoon(): LocalDateTime {
    val desiredTime = LocalTime.NOON
    var nextDayAtNoon: LocalDateTime = this.toLocalDate().atTime(desiredTime)
    if (this.toLocalTime().isAfter(desiredTime))
        nextDayAtNoon = nextDayAtNoon.plus(1, ChronoUnit.DAYS)
    return nextDayAtNoon.also { "${it.dayOfMonth}/${it.month}/${it.year}" }
}
