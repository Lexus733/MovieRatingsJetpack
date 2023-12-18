package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Error(message: String) {
    Column {
        Text(text = message)
    }
}