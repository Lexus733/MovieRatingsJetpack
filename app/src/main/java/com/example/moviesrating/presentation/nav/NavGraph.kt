package com.example.moviesrating.presentation.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesrating.presentation.screens.home.HomeScreen
import com.example.moviesrating.presentation.screens.savewatch.SaveWatchScreen
import com.example.moviesrating.presentation.screens.search.SearchScreen
import com.example.moviesrating.utils.RouteConst

@Composable
fun NavGraph(
    navHostController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = RouteConst.HOME_SCREEN,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(RouteConst.HOME_SCREEN) {
            HomeScreen()
        }
        composable(RouteConst.SEARCH_SCREEN) {
            SearchScreen()
        }
        composable(RouteConst.SAVE_WATCH_SCREEN) {
            SaveWatchScreen()
        }
    }
}
