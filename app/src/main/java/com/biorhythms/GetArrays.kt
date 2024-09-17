package com.biorhythms

import com.github.mikephil.charting.data.Entry
import kotlin.math.*

const val PHYSICAL_COEFF = 2 * PI / 23.6884
const val EMOTIONAL_COEFF = 2 * PI / 28.4261
const val INTELLECTUAL_COEFF = 2 * PI / 33.2103

fun getArrays(data: DataForGraph) {
    val start = data.dayStart
    for (i in 0..40) {
        val xValue = i.toFloat()
        data.physical.add(Entry(xValue, (sin(PHYSICAL_COEFF * (start + i)) * 100).toFloat(), "Привіт"))
        data.emotional.add(Entry(xValue, (sin(EMOTIONAL_COEFF * (start + i)) * 100).toFloat(), ""))
        data.intellectual.add(
            Entry(
                xValue,
                (sin(INTELLECTUAL_COEFF * (start + i)) * 100).toFloat(),
                ""
            )
        )
    }
}
