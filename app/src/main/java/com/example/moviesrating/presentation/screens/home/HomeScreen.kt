package com.example.moviesrating.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val moviesState by homeViewModel.movieState.collectAsState()
    homeViewModel.getMovieList()
    Column {
        Text(text = moviesState.toString())
    }
}