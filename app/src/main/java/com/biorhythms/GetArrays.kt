package com.biorhythms

import co.yml.charts.common.model.Point
import java.time.temporal.ChronoUnit
import kotlin.math.*

fun getArrays(data: DataForGraph) {
    val numberOfDays: Int = ChronoUnit.DAYS.between(data.dob, data.nowDay).toInt()
    val dayStart = numberOfDays - 10

    for (i in dayStart..numberOfDays + 30) {
        val xValue = (i - dayStart).toFloat()
        data.physical.add(Point(xValue, (sin(PHYSICAL_COEFF * i) * 10).toFloat(), ""))
        data.emotional.add(Point(xValue, (sin(EMOTIONAL_COEFF * i) * 10).toFloat(), ""))
        data.intellectual.add(Point(xValue, (sin(INTELLECTUAL_COEFF * i) * 10).toFloat(), ""))
    }
}
