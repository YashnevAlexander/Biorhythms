package com.biorhythms

import androidx.lifecycle.ViewModel
import co.yml.charts.common.model.Point
import java.time.LocalDate

data class DataForGraph(
    var dob: LocalDate,
    var nowDay : LocalDate,
    var physical: MutableList<Point> = mutableListOf(),
    var emotional:  MutableList<Point> = mutableListOf(),
    var intellectual:  MutableList<Point> = mutableListOf()
)

class MyViewModel : ViewModel() {
    val dataForGraph: DataForGraph by lazy {
        DataForGraph(
            dob = LocalDate.now(), // Початкове значення, яке може бути змінене пізніше
            nowDay = LocalDate.now(),
            physical = mutableListOf(),
            emotional = mutableListOf(),
            intellectual = mutableListOf()
        )
    }

    fun updateDate(dob: LocalDate) {
        dataForGraph.dob = dob
        dataForGraph.nowDay = LocalDate.now()
        clearData()
        getArrays(dataForGraph)
    }

    private fun clearData() {
        dataForGraph.physical.clear()
        dataForGraph.emotional.clear()
        dataForGraph.intellectual.clear()
    }
}
