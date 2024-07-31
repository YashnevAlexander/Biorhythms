package com.biorhythms

import android.app.Activity
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun BiorhythmsView(navController: NavController) {
    var selectedDate by remember { mutableStateOf(Date()) }

    val context = LocalContext.current

    val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) }
    val formattedDate = dateFormat.format(selectedDate)

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
                        calendar.set(year, month, dayOfMonth)
                        selectedDate = calendar.time
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Оберіть дату свого народження")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Обрано дату: $formattedDate")

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { navController.navigate("biorhythms_draw") }) {
                Text("Показати графічно")
            }
            Button(onClick = {
                (context as? Activity)?.finish()
            }) {
                Text("Завершити роботу")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BiorhythmsViewPreview() {
    val context = LocalContext.current
    val navController = TestNavHostController(context)
    BiorhythmsView(navController)
}
