package com.example.moviesrating.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesrating.R
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailPreview
import com.example.moviesrating.presentation.ui.ThemePreviews
import com.example.moviesrating.presentation.ui.components.Loading
import com.example.moviesrating.presentation.ui.components.PosterImage
import com.example.moviesrating.presentation.ui.components.Rating
import com.example.moviesrating.presentation.ui.components.SearchField
import com.example.moviesrating.presentation.ui.components.SearchSubTitle
import com.example.moviesrating.presentation.ui.components.SearchTitle

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchState = viewModel.viewState.collectAsState()
    val text = viewModel.searchTextState.collectAsState()

    Box {
        Column {
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = text.value,
                onValueChanged = {
                    viewModel.updateSearchText(it)
                }
            )
            when (val state = searchState.value) {
                is SearchViewState.NoItems -> NoItems()
                is SearchViewState.Loading -> Loading()
                is SearchViewState.Display -> SearchResult(state.list)
            }
        }
    }

    LaunchedEffect(key1 = searchState, block = {
        viewModel.obtainIntent(intent = SearchViewIntent.EnterScreen)
    })
}

@Composable
private fun SearchResult(list: List<EntityMovieDetail>) {
    LazyColumn {
        items(list) {
            SearchItem(it)
        }
    }
}

@ThemePreviews
@Composable
private fun NoItems() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.image_no_results),
                contentDescription = null
            )
            SearchTitle()
            SearchSubTitle()
        }
    }
}

@ThemePreviews
@Composable
private fun SearchItem(@PreviewParameter(EntityMovieDetailPreview::class) entityMovieDetail: EntityMovieDetail) {
    Row {
        PosterImage(entityMovieDetail = entityMovieDetail) {}
        Column {
            Text(
                text = entityMovieDetail.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(R.font.poppins)),
                color = Color.White,
                modifier = Modifier.fillMaxSize()
            )
            Rating(rating = entityMovieDetail.rating.toString())
            ItemInfo(
                icon = R.drawable.ic_ticket,
                text = entityMovieDetail.gen.first().genre
            )
            ItemInfo(
                icon = R.drawable.ic_calendar_blank,
                text = entityMovieDetail.year.toString()
            )
            ItemInfo(icon = R.drawable.ic_clock, text = entityMovieDetail.movie_length)
        }
    }
}

@Composable
private fun ItemInfo(icon: Int, text: String) {
    Row {
        Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color.White)
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = text,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins)
            ),
            fontSize = 12.sp,
            fontWeight = FontWeight.W400
        )
    }
}
