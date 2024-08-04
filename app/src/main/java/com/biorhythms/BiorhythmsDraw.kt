package com.biorhythms

//import com.biorhythms.ui.compositions.AppBarWithBackButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import co.yml.charts.axis.*
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import com.biorhythms.ui.theme.YChartsTheme

const val steps = 10

@Composable
fun BiorhythmsDraw(navController: NavController, viewModel: MyViewModel) {
    val listPhysical = viewModel.dataForGraph.physical
    val listEmotional = viewModel.dataForGraph.emotional
    val listIntellectual = viewModel.dataForGraph.intellectual

    val xAxisData = AxisData.Builder()
        .axisStepSize(20.dp)
        .axisLineColor(Color.Blue)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(16.dp)
        .build()
    val yAxisData = AxisData.Builder()
        .steps(steps)
        .axisStepSize(50.dp)
        .axisLineColor(Color.Blue)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(20.dp)
        .build()
    YChartsTheme {
        val lineChartData = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = listPhysical,
                        LineStyle(color = Color.Green),

                    ),
                    Line(
                        dataPoints = listEmotional,
                        LineStyle(color = Color.Blue)
                    ),
                    Line(
                        dataPoints = listIntellectual,
                        LineStyle(color = Color.Red)
                    )
                )
            ),
            xAxisData = xAxisData,
            yAxisData =  yAxisData,
            gridLines = GridLines(color = Color.LightGray)
        )
        LineChart(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
            lineChartData = lineChartData)
    }
}
