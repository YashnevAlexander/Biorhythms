package com.biorhythms

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import androidx.navigation.NavController
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@Composable
fun BiorhythmsDraw(navController: NavController, viewModel: MyViewModel) {
    val context = LocalContext.current
    val chart = remember { LineChart(context) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val isPortrait =
        configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT

    val dataForGraph = viewModel.dataForGraph

    val dataSet1 = LineDataSet(dataForGraph.physical, "Фізичний").apply {
        color = Color.Green.toArgb()
        setDrawCircles(false)
        setDrawValues(false)
    }

    val dataSet2 = LineDataSet(dataForGraph.emotional, "Емоційний").apply {
        color = Color.Red.toArgb()
        setDrawCircles(false)
        setDrawValues(false)
    }

    val dataSet3 = LineDataSet(dataForGraph.intellectual, "Інтелектуальний").apply {
        color = Color.Blue.toArgb()
        setDrawCircles(false)
        setDrawValues(false)
    }

    val lineData = LineData(dataSet1, dataSet2, dataSet3)
    chart.data = lineData
// Set general description
    chart.description.text = "Biorhythm Cycles" // Текст опису
    chart.description.textSize = 14f // Розмір тексту опису
    chart.description.textColor = Color.DarkGray.toArgb() // Колір тексту опису
    chart.description.isEnabled = true // Вмикає опис

    // Configure X Axis ------------------------------------------------------------------->
    val xAxis = chart.xAxis
    xAxis.position = XAxis.XAxisPosition.BOTTOM
    xAxis.labelRotationAngle = 0f
    xAxis.setDrawGridLines(false)
    xAxis.setDrawAxisLine(true)
    xAxis.setDrawLabels(true)
    xAxis.setGranularity(1f)
    xAxis.setLabelCount(
        if (isPortrait) 10 else 20,
        true
    ) // Встановлює кількість міток залежно від орієнтації
    xAxis.textColor = Color.Black.toArgb() // Колір тексту осі
//    xAxis.textSize = 12f // Розмір тексту осі
//    xAxis.valueFormatter = IndexAxisValueFormatter(listOf("Day 1", "Day 2", "Day 3")) // Форматування підписів

    // Configure Y Axis------------------------------------------------------------------->
    val leftAxis = chart.axisLeft
    leftAxis.setDrawGridLines(true)
    leftAxis.setDrawAxisLine(true)
    leftAxis.setDrawLabels(true)
    leftAxis.axisMinimum = -100f
    leftAxis.axisMaximum = 100f
    leftAxis.textColor = Color.Black.toArgb() // Колір тексту вісі   Y

// Create a LimitLine for Y = 0
    val zeroLine = LimitLine(0f, "0").apply {
        lineWidth = 2f // Встановлює товщину лінії
        lineColor = Color.Black.toArgb() // Колір лінії
    }
    leftAxis.addLimitLine(zeroLine)
    

    val rightAxis = chart.axisRight
    rightAxis.isEnabled = false

    // Enable scrolling and zooming
    chart.setDragEnabled(true)
    chart.setScaleEnabled(true)
    chart.isScaleXEnabled = true
    chart.isScaleYEnabled = false

    // Set the visible range for the X axis to enable scrolling
    chart.setVisibleXRangeMaximum(if (isPortrait) 10f else 30f) // Змінює видимий діапазон залежно від орієнтації
    chart.moveViewToX(0f)

    chart.invalidate()

    Box(
        modifier = Modifier.fillMaxSize() // Використовуємо Box для розміщення кнопки
    ) {
        // Кнопка повернення
        IconButton(
            onClick = { navController.popBackStack() }, // Повернення на попередній екран
            modifier = Modifier
                .align(Alignment.TopStart) // Розташовуємо в лівому верхньому куті
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { chart },
                modifier = Modifier
                    .width(if (isPortrait) screenWidth * 0.95f else screenWidth * 0.8f) // Змінює ширину залежно від орієнтації
                    .height(if (isPortrait) screenHeight * 0.5f else screenHeight * 0.8f) // Змінює висоту залежно від орієнтації
                    .padding(16.dp)
            )
        }
    }
}
