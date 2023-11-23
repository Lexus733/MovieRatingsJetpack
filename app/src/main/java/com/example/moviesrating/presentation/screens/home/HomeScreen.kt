package com.example.moviesrating.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesrating.presentation.ui.components.PosterImage
import com.example.moviesrating.presentation.ui.theme.BackgroundColor

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val moviesState by homeViewModel.homeViewState.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        when (val state = moviesState) {
            is HomeViewState.NoItems -> NoItems()
            is HomeViewState.Loading -> Loading()
            is HomeViewState.Error -> Error(state)
            is HomeViewState.Display -> Display(state)
        }
    }
}

@Composable
fun NoItems() {
    Column {
        Text(text = "No movies")
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun Error(state: HomeViewState.Error) {
    Toast.makeText(
        LocalContext.current,
        "Message: ${state.message ?: "Empty message"} + Code: ${state.code ?: -999}",
        Toast.LENGTH_LONG
    ).show()
}

@Composable
fun Display(state: HomeViewState.Display) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        items(state.items) {
            PosterImage(it)
        }
    }
}
