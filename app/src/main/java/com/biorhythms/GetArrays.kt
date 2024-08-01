package com.biorhythms

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.sin

fun getArrays(data: DataForGraph) {
    val numberOfDays : Int = ChronoUnit.DAYS.between(data.dob, LocalDate.now()).toInt()
    val dayStart = numberOfDays - 10
    val indices = (dayStart..numberOfDays + 30).toList()

    data.physical.addAll(indices.map { sin(it.toDouble() / 23.69) })
    data.emotional.addAll(indices.map { sin(it.toDouble() / 28.43) })
    data.intellectual.addAll(indices.map { sin(it.toDouble() / 33.16) })
}
