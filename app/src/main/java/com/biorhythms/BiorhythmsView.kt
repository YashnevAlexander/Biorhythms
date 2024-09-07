package com.biorhythms

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import java.time.LocalDate
import java.util.*

@Composable
fun BiorhythmsView(navController: NavController, viewModel: MyViewModel) {
    val context = LocalContext.current
    var selectedDate by rememberSaveable { mutableStateOf(viewModel.dataForGraph.dob.takeIf { it != LocalDate.now() }) }
    val calendarMaxD = Calendar.getInstance()
    calendarMaxD.add(Calendar.DAY_OF_YEAR, -10)
    val maxDate = calendarMaxD.timeInMillis

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Дізнайтеся про свої біоритми", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val calendar = Calendar.getInstance()
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                        viewModel.updateDate(selectedDate!!)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.maxDate = maxDate
                datePickerDialog.show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Оберіть дату свого народження")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Обрано дату народження: ${selectedDate ?: ""}", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("biorhythms_draw")
                },
                enabled = selectedDate != null,
                modifier = Modifier.weight(1f)
            ) {
                Text("Показати")
            }
            Spacer(modifier = Modifier.width(36.dp))
            Button(
                onClick = {
                    (context as? Activity)?.finish()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Завершити")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BiorhythmsViewPreview() {
    val context = LocalContext.current
    val navController = TestNavHostController(context)
    val viewModel = MyViewModel()
    BiorhythmsView(navController, viewModel)
}
