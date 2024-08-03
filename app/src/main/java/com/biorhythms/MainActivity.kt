package com.biorhythms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import kotlin.math.PI

const val PHYSICAL_COEFF = 2 * Math.PI / 23
const val EMOTIONAL_COEFF = 2 * Math.PI / 28
const val INTELLECTUAL_COEFF = 2 * Math.PI / 33

class MainActivity : ComponentActivity() {
    private val myViewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel = myViewModel)
        }
    }
}
