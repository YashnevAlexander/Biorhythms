package com.biorhythms

import com.github.mikephil.charting.data.Entry
import kotlin.math.*

fun getArrays(data: DataForGraph) {
    val start = data.dayStart
    for (i in 0..40) {
        val xValue = i.toFloat()
        data.physical.add(Entry(xValue, (sin(PHYSICAL_COEFF * (start + i)) * 10).toFloat(), ""))
        data.emotional.add(Entry(xValue, (sin(EMOTIONAL_COEFF * (start + i)) * 10).toFloat(), ""))
        data.intellectual.add(
            Entry(
                xValue,
                (sin(INTELLECTUAL_COEFF * (start + i)) * 10).toFloat(),
                ""
            )
        )
    }
}
