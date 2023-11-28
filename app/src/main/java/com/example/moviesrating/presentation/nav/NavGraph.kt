package com.example.moviesrating.presentation.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.presentation.screens.detail.MovieDetailScreen
import com.example.moviesrating.presentation.screens.detail.MovieDetailViewModel
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
        startDestination = RouteConst.HOME,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(RouteConst.HOME) {
            HomeScreen(navController = navHostController)
        }
        composable(RouteConst.SEARCH) {
            SearchScreen(navController = navHostController)
        }
        composable(RouteConst.SAVE_WATCH) {
            SaveWatchScreen(navController = navHostController)
        }
        composable(RouteConst.MOVIE_DETAIL) {
            val movieDetail = navHostController.previousBackStackEntry?.savedStateHandle?.get<EntityMovieDetail>(RouteConst.MOVIE_DETAIL)
            val viewModel : MovieDetailViewModel = hiltViewModel()
            if (movieDetail != null) viewModel.updateViewModel(movieDetail)
            MovieDetailScreen(navController = navHostController, viewModel)
        }
    }
}
