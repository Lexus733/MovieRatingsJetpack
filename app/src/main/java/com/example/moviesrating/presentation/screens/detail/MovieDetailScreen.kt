package com.example.moviesrating.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviesrating.presentation.screens.home.Loading
import com.example.moviesrating.presentation.ui.theme.BackgroundColor

@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    movieDetailViewModel : MovieDetailViewModel = hiltViewModel()
) {
    val moviesState by movieDetailViewModel.movieDetailViewState.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        when (val state = moviesState) {
            is MovieDetailViewState.Loading -> Loading()
            is MovieDetailViewState.Display -> Display(state)
        }
    }

    LaunchedEffect(key1 = moviesState, block = {
        movieDetailViewModel.obtainIntent(intent = MovieDetailIntent.EnterScreen)
    })
}

@Composable
private fun Display(state: MovieDetailViewState.Display) {
    Text(text = state.movie.toString())
}