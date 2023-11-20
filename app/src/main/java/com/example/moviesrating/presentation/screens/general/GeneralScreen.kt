package com.example.moviesrating.presentation.screens.general

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GeneralScreen(
    generalViewModel: GeneralViewModel = viewModel(factory = GeneralViewModel.Factory)
) {
    val moviesState by generalViewModel.movieState.collectAsState()
    generalViewModel.getMovieList()
    Column {
        Text(text = moviesState.toString())
    }
}