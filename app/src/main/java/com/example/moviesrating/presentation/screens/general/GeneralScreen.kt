package com.example.moviesrating.presentation.screens.general

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GeneralScreen(
    generalViewModel: GeneralViewModel = hiltViewModel()
) {
    val moviesState by generalViewModel.movieState.collectAsState()
    generalViewModel.getMovieList()
    Column {
        Text(text = moviesState.toString())
    }
}