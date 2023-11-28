package com.example.moviesrating.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Preview(showBackground = true)
@Composable
fun SearchScreen(navController: NavHostController) {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "SearchScreen().javaClass.toString()"
    )
}