package com.example.moviesrating.presentation.screens.detail

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

sealed class MovieDetailIntent {

    data class EnterScreen(val movie: EntityMovieDetail?) : MovieDetailIntent()
}
