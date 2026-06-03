package io.github.kevinah95.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import io.github.kevinah95.myapplication.screens.ContadorScreen

@Composable
fun App() {
    MaterialTheme {
        ContadorScreen()
    }
}