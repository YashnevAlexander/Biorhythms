package com.biorhythms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import co.yml.charts.axis.*
//import co.yml.charts.ui.linechart.SingleLineChart

@Composable
fun BiorhythmsDraw(navController: NavController, viewModel: MyViewModel) {
    val listPhysical = viewModel.dataForGraph.physical
    val listEmotional = viewModel.dataForGraph.emotional
    val listIntellectual = viewModel.dataForGraph.intellectual

    Spacer(modifier = Modifier.height(100.dp))
    val xAxisData = AxisData.Builder()
        .axisStepSize(50.dp)
        .steps(listPhysical.size-1)
        .build()
    val yAxisData = AxisData.Builder()
        .axisStepSize(50.dp)
        .steps(10)
        .labelData { i -> i.toString() }
        .build()
//    LineChart(modifier = , lineChartData = setOf(phisCoeff) )
    Spacer(modifier = Modifier.height(16.dp))

    Button(onClick = { navController.popBackStack() }) {
        Text("Повернутися")
    }
}

@Preview(showBackground = true)
@Composable
fun BiorhythmsDrawPreview() {
    val context = LocalContext.current
    val navController = TestNavHostController(context)
    val viewModel = MyViewModel()
    BiorhythmsDraw(navController, viewModel)
}

//Column(
//modifier = Modifier
//.fillMaxSize()
//.padding(16.dp),
//verticalArrangement = Arrangement.Center,
//horizontalAlignment = Alignment.CenterHorizontally
//) {
//    Text(text = "Графіки біоритмів", fontSize = 24.sp)
//    // Тут буде ваш код для відображення графіків
//    Spacer(modifier = Modifier.height(16.dp))
//
//    Button(onClick = { navController.popBackStack() }) {
//        Text("Повернутися")
//    }
//}