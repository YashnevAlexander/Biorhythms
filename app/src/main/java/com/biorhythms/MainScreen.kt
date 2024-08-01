package com.biorhythms

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(viewModel: MyViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "biorhythms_view") {
        composable("biorhythms_view") { BiorhythmsView(navController, viewModel) }
        composable("biorhythms_draw") { BiorhythmsDraw(navController, viewModel) }
    }
}
