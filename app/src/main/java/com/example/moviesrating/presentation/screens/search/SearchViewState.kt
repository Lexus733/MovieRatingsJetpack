package com.example.moviesrating.presentation.screens.search

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

sealed class SearchViewState() {
    object Loading : SearchViewState()
    data class Display(val list: List<EntityMovieDetail>) : SearchViewState()
    object NoItems : SearchViewState()
}