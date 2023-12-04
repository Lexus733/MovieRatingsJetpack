package com.example.moviesrating.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchState = viewModel.searchViewState.collectAsState()

    Box {
        Column {
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = viewModel.searchText,
                onValueChanged = {
                    viewModel.updateSearchText(it)
                },
                onIconClick = {
                    viewModel.getDataBySearchText()
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
    Column {
        Image(
            painter = painterResource(id = R.drawable.image_no_results),
            contentDescription = null
        )
        SearchTitle()
        SearchSubTitle()
    }
}

@ThemePreviews
@Composable
private fun SearchItem(@PreviewParameter(EntityMovieDetailPreview::class) entityMovieDetail: EntityMovieDetail) {
    Row {
        PosterImage(entityMovieDetail = entityMovieDetail) {}
        Column {
            Text(text = entityMovieDetail.title)
            Rating(rating = entityMovieDetail.rating.toString())
            ItemInfo(icon = R.drawable.ic_ticket, text = entityMovieDetail.gen.first().genre)
            ItemInfo(icon = R.drawable.ic_calendar_blank, text = entityMovieDetail.year.toString())
            ItemInfo(icon = R.drawable.ic_clock, text = entityMovieDetail.movie_length)
        }
    }
}

@Composable
private fun ItemInfo(icon: Int, text: String) {
    Row {
        Icon(painter = painterResource(id = icon), contentDescription = null)
        Text(text = text)
    }
}
