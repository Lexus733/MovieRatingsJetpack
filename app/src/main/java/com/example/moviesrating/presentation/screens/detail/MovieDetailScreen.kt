package com.example.moviesrating.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.moviesrating.R
import com.example.moviesrating.presentation.screens.home.Loading
import com.example.moviesrating.presentation.ui.components.PosterImage
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.presentation.ui.theme.MovieDetailCardRateColor
import com.example.moviesrating.presentation.ui.theme.MovieDetailDescriptionColor
import com.example.moviesrating.presentation.ui.theme.MovieDetailDividerColor
import com.example.moviesrating.presentation.ui.theme.MovieDetailRateColor

@Composable
fun MovieDetailScreen(
    navController: NavHostController, movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val moviesState by movieDetailViewModel.movieDetailViewState.collectAsState()

    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
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
    Column {
        BannerWithBaseInfo(state = state)
        DescriptionInfo(state = state)
        AdditionalInfoTabRow(state = state)
    }
}

@Composable
private fun BannerWithRate(state: MovieDetailViewState.Display, modifier: Modifier) {
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
            Row(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .padding(end = 4.dp),
                    painter = painterResource(id = R.drawable.ic_star_rating),
                    contentDescription = null,
                    tint = MovieDetailRateColor
                )
                Text(
                    text = state.movie.rating.toString(),
                    color = MovieDetailRateColor,
                    fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                    fontWeight = FontWeight(600),
                    fontSize = 12.sp,
                    letterSpacing = 0.12.sp
                )
            }
        }
    }
}

@Composable
private fun BaseInfo(state: MovieDetailViewState.Display, modifier: Modifier) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            PosterImage(entityMovieDetail = state.movie) { }
            Text(
                text = state.movie.title,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins)),
                fontWeight = FontWeight(600),
                color = Color.White
            )
        }
    }
}

@Composable
private fun DescriptionInfo(state: MovieDetailViewState.Display) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            DescriptionInfoItem(
                id = R.drawable.ic_calendar_blank,
                text = state.movie.year.toString()
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp), thickness = 1.dp, color = MovieDetailDividerColor
            )
            DescriptionInfoItem(
                id = R.drawable.ic_clock,
                text = state.movie.movie_length
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp), thickness = 1.dp, color = MovieDetailDividerColor
            )
            DescriptionInfoItem(
                id = R.drawable.ic_ticket,
                text = state.movie.gen.first().genre
            )
        }
    }
}

@Composable
private fun DescriptionInfoItem(id: Int, text: String) {
    Row {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MovieDetailDescriptionColor,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = text,
            color = MovieDetailDescriptionColor,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            fontWeight = FontWeight(500),
            letterSpacing = 0.12.sp
        )
    }
}

@Composable
private fun BannerWithBaseInfo(state: MovieDetailViewState.Display) {
    ConstraintLayout {
        val (banner, info) = createRefs()

        BannerWithRate(state = state, Modifier.constrainAs(banner) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        BaseInfo(state = state, Modifier.constrainAs(info) {
            top.linkTo(banner.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(banner.bottom)
        })
    }
}

@Composable
private fun AdditionalInfoTabRow(state: MovieDetailViewState.Display) {
    val tabs = listOf("About Movie", "Cast")
    var tabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(selectedTabIndex = tabIndex,
                containerColor = BackgroundColor,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = {
                        Text(
                            modifier = Modifier,
                            text = title,
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
            when (tabIndex) {
                0 -> AdditionalInfoTabAbout(state)
                1 -> AdditionalInfoTabCast(state)
            }
        }
    }
}

@Composable
private fun AdditionalInfoTabAbout(
    state: MovieDetailViewState.Display
) {
    Text(
        text = state.movie.description, style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(400),
            color = Color.White,
        ),
        modifier = Modifier
            .padding(top = 24.dp, start = 8.dp, end = 8.dp)
    )
}

@Composable
private fun AdditionalInfoTabCast(
    state: MovieDetailViewState.Display
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(500.dp),
        content = {
            items(state.movie.castList!!) {
                AdditionalInfoTabCastItem(
                    imageUrl = it.image_url ?: "",
                    name = it.name
                )
            }
        })
}

@Composable
private fun AdditionalInfoTabCastItem(
    imageUrl: String,
    name: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier.size(100.dp),
            shape = CircleShape
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = name,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight(500),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
