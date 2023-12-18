package com.example.moviesrating.presentation.screens.detail

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

sealed class MovieDetailViewState {

    data object Loading : MovieDetailViewState()

    data object Error : MovieDetailViewState()

    data class Display(
        val movie: EntityMovieDetail
    ) : MovieDetailViewState()
}
