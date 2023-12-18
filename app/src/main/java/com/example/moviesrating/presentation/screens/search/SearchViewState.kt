package com.example.moviesrating.presentation.screens.search

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

sealed class SearchViewState {
    data object Loading : SearchViewState()
    data class Display(val list: List<EntityMovieDetail>) : SearchViewState()
    data object NoItems : SearchViewState()
}
