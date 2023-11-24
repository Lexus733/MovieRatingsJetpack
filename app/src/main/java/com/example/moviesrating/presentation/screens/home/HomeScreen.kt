package com.example.moviesrating.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesrating.R
import com.example.moviesrating.presentation.ui.components.PosterImage
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import kotlinx.coroutines.launch

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
    Column {
        Text(text = state.message.toString())
    }
}

@Composable
fun Display(state: HomeViewState.Display) {
    Column {
        PopularMoviesLazyRow(state)
        GenresTabRow(state)
    }
}

@Composable
fun PopularMoviesLazyRow(state: HomeViewState.Display) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        items(state.items) {
            PosterImage(it)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenresTabRow(state: HomeViewState.Display) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            state.genMap.keys.size
        },
        initialPageOffsetFraction = 0f
    )

    val pagerPage = remember {
        mutableIntStateOf(0)
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage, block = {
        pagerPage.intValue = pagerState.currentPage
    })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        ScrollableTabRow(
            selectedTabIndex = pagerPage.intValue,
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
                    selected = pagerState.currentPage == index,
                    onClick = {
                        pagerPage.intValue = index
                        scope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val currentPage = pagerState.currentPage) {
            else -> GenreLazyGrid(currentPage, state)
        }
    }
}

@Composable
fun GenreLazyGrid(
    currentPage: Int,
    state: HomeViewState.Display
) {
    //Оптимизировать эту хрень и asyncImage чтобы кэшировалось
    val gen = state.genMap.keys.elementAt(currentPage)
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        content = {
            items(state.genMap[gen]!!) {
                PosterImage(dataEntityMovieByImdbId = it)
            }
        })
}
