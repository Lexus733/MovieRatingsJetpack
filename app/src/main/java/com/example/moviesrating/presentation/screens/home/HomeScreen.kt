package com.example.moviesrating.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesrating.R
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.presentation.ui.components.Loading
import com.example.moviesrating.presentation.ui.components.PosterImage
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.utils.RouteConst

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
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
            is HomeViewState.Error -> Error(state.message.toString())
            is HomeViewState.Display -> Display(state) {
                navController.currentBackStackEntry?.savedStateHandle?.set(RouteConst.MOVIE_DETAIL, it)
                navController.navigate(RouteConst.MOVIE_DETAIL)
            }
        }
    }

    LaunchedEffect(key1 = moviesState, block = {
        homeViewModel.obtainIntent(intent = HomeIntent.EnterScreen)
    })
}

@Composable
private fun NoItems() {
    Column {
        Text(text = "No movies")
    }
}

@Composable
private fun Display(state: HomeViewState.Display, onPosterClick : (EntityMovieDetail) -> Unit) {
    Column {
        PopularMoviesLazyRow(state, onPosterClick)
        GenresTabRow(state, onPosterClick)
    }
}

@Composable
private fun PopularMoviesLazyRow(state: HomeViewState.Display, onPosterClick: (EntityMovieDetail) -> Unit) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        items(state.items) {
            PosterImage(it) {
                onPosterClick.invoke(it)
            }
        }
    }
}

@Composable
private fun GenresTabRow(state: HomeViewState.Display, onPosterClick: (EntityMovieDetail) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp,
            containerColor = BackgroundColor,
            divider = {}
        ) {
            state.genMap.keys.forEachIndexed { index, title ->
                Tab(text = {
                    Text(
                        modifier = Modifier,
                        text = title.genre,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(500)
                    )
                },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
    }
    GenreLazyGrid(tabIndex, state, onPosterClick)
}

@Composable
private fun GenreLazyGrid(
    currentPage: Int,
    state: HomeViewState.Display,
    onPosterClick: (EntityMovieDetail) -> Unit
) {
    //TODO Оптимизировать эту хрень
    val gen = state.genMap.keys.elementAt(currentPage)
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        content = {
            items(state.genMap[gen]!!) {
                PosterImage(entityMovieDetail = it) {
                    onPosterClick.invoke(it)
                }
            }
        })
}

@Composable
private fun sad(){

}
