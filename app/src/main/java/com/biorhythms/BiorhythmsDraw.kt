package com.biorhythms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import androidx.navigation.NavController
//import com.github.mikephil.charting.formatter.ValueFormatter
//import com.github.mikephil.charting.components.AxisBase
//import com.github.mikephil.charting.components.YAxis
//import com.github.mikephil.charting.data.Entry

@Composable
fun BiorhythmsDraw(navController: NavController, viewModel: MyViewModel) {
    val context = LocalContext.current
    val chart = remember { LineChart(context) }

    val dataForGraph = viewModel.dataForGraph

    val dataSet1 = LineDataSet(dataForGraph.physical, "Physical cycle").apply {
        color = Color.Red.toArgb()
        setDrawCircles(false)
    }

    val dataSet2 = LineDataSet(dataForGraph.emotional, "Emotional cycle").apply {
        color = Color.Blue.toArgb()
        setDrawCircles(false)
    }

    val dataSet3 = LineDataSet(dataForGraph.intellectual, "Intellectual cycle").apply {
        color = Color.Green.toArgb()
        setDrawCircles(false)
    }

    val lineData = LineData(dataSet1, dataSet2, dataSet3)
    chart.data = lineData

    // Configure X Axis
    val xAxis = chart.xAxis
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.labelRotationAngle = 45f
    xAxis.setDrawGridLines(false)
    xAxis.setDrawAxisLine(true)
    xAxis.setDrawLabels(true)
    xAxis.setGranularity(1f) // Set the interval for axis labels
    xAxis.setLabelCount(20, true) // Set the number of labels to be displayed

    // Configure Y Axis
    val leftAxis = chart.axisLeft
    leftAxis.setDrawGridLines(true)
    leftAxis.setDrawAxisLine(true)
    leftAxis.setDrawLabels(true)
    leftAxis.axisMinimum = -10f
    leftAxis.axisMaximum = 10f

    val rightAxis = chart.axisRight
    rightAxis.isEnabled = false // Disable right Y axis

    // Enable scrolling and zooming
    chart.setDragEnabled(true)
    chart.setScaleEnabled(true)
    chart.isScaleXEnabled = true
    chart.isScaleYEnabled = false // Disable vertical scaling

    // Set the visible range for the X axis to enable scrolling
    chart.setVisibleXRangeMaximum(20f) // Display 10 points at a time
    chart.moveViewToX(0f) // Start view at the beginning of the data

    chart.invalidate()

    AndroidView(
        factory = { chart },
        modifier = Modifier.fillMaxSize()
    )
}
