package com.example.moviesrating.presentation.screens.general

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.moviesrating.presentation.nav.NavGraph
import com.example.moviesrating.presentation.nav.bottomnav.BottomNavigationCustom
import com.example.moviesrating.presentation.ui.theme.MoviesRatingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MoviesRatingTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigationCustom(
                            navController = navController
                        )
                    }
                ) { innerPadding ->
                    NavGraph(
                        navHostController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}