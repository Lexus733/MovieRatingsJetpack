package com.example.moviesrating.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.moviesrating.R
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.presentation.ui.components.Error
import com.example.moviesrating.presentation.ui.components.Loading
import com.example.moviesrating.presentation.ui.components.PosterImage
import com.example.moviesrating.presentation.ui.components.Rating
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.presentation.ui.theme.MovieDetailCardRateColor

@Composable
fun MovieDetailScreen(
    movieDetail: EntityMovieDetail?,
    modifier: Modifier = Modifier,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val moviesState by movieDetailViewModel.movieDetailViewState.collectAsState()

    Box(
        modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        when (val state = moviesState) {
            is MovieDetailViewState.Loading -> Loading()
            is MovieDetailViewState.Display -> Display(state)
            is MovieDetailViewState.Error -> Error(
                message = stringResource(id = R.string.screen_movie_detail_error)
            )
        }
    }

    LaunchedEffect(key1 = moviesState, block = {
        movieDetailViewModel.obtainIntent(intent = MovieDetailIntent.EnterScreen(movieDetail))
    })
}

@Composable
private fun Display(state: MovieDetailViewState.Display) {
    Column {
        BannerWithBaseInfo(state = state)
        DescriptionInfo(state = state)
        AdditionalInfoTabRow(state = state)
    }
}

@Composable
private fun BannerWithRate(
    state: MovieDetailViewState.Display,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = state.movie.banner,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
            contentScale = ContentScale.FillBounds,
        )
        Card(
            modifier = Modifier
                .padding(8.dp)
                .padding(8.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MovieDetailCardRateColor
            ),
        ) {
            Rating(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                rating = state.movie.rating.toString()
            )
        }
    }
}

@Composable
private fun BaseInfo(
    state: MovieDetailViewState.Display,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            PosterImage(
                modifier = Modifier,
                onClick = {},
                entityMovieDetail = state.movie
            )
            Text(
                text = state.movie.title,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight.W600,
                color = Color.White
            )
        }
    }
}

@Composable
private fun BannerWithBaseInfo(state: MovieDetailViewState.Display) {
    ConstraintLayout {
        val (banner, info) = createRefs()

        BannerWithRate(
            state = state,
            Modifier.constrainAs(banner) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        BaseInfo(
            state = state,
            Modifier.constrainAs(info) {
                top.linkTo(banner.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(banner.bottom)
            }
        )
    }
}
