package com.biorhythms

import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class DataForGraph(
    var dob: LocalDate,
    var nowDay: LocalDate,
    var physical: MutableList<Entry> = mutableListOf(),
    var emotional: MutableList<Entry> = mutableListOf(),
    var intellectual: MutableList<Entry> = mutableListOf(),
    var dayStart: Int
)

class MyViewModel : ViewModel() {
    val dataForGraph: DataForGraph by lazy {
        DataForGraph(
            dob = LocalDate.now(), // Початкове значення, яке може бути змінене пізніше
            nowDay = LocalDate.now(),
            physical = mutableListOf(),
            emotional = mutableListOf(),
            intellectual = mutableListOf(),
            dayStart = 0
        )
    }

    fun updateDate(dob: LocalDate) {
        dataForGraph.dob = dob
        dataForGraph.nowDay = LocalDate.now()
        val numberOfDays: Int = ChronoUnit.DAYS.between(dataForGraph.dob, dataForGraph.nowDay).toInt()
        dataForGraph.dayStart = numberOfDays - 10
        clearData()
        getArrays(dataForGraph)
    }

    private fun clearData() {
        dataForGraph.physical.clear()
        dataForGraph.emotional.clear()
        dataForGraph.intellectual.clear()
    }
}
