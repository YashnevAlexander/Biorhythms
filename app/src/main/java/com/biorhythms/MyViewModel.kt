package com.biorhythms

import androidx.lifecycle.ViewModel
import java.time.LocalDate

data class DataForGraph(
    var dob: LocalDate,
    var physical: MutableList<Double> = mutableListOf(),
    var emotional: MutableList<Double> = mutableListOf(),
    var intellectual: MutableList<Double> = mutableListOf()
)

class MyViewModel : ViewModel() {
    var dataForGraph = DataForGraph(LocalDate.now())

    fun updateDate(dob: LocalDate) {
        dataForGraph.dob = dob
        getArrays(dataForGraph)
    }
}
