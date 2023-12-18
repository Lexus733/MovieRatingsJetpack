package com.example.moviesrating.presentation.screens.search

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import kotlinx.collections.immutable.ImmutableList

sealed class SearchViewState {
    data object Loading : SearchViewState()
    data class Display(val list: ImmutableList<EntityMovieDetail>) : SearchViewState()
    data object NoItems : SearchViewState()
}
