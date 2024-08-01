package com.biorhythms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController

@Composable
fun BiorhythmsDraw(navController: NavController, viewModel: MyViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Графіки біоритмів", fontSize = 24.sp)
        // Тут буде ваш код для відображення графіків

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Повернутися")
        }
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
