package com.example.moviesrating.presentation.screens.home

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId

sealed class HomeIntent {

    object EnterScreen : HomeIntent()

    data class OnMovieClick(val data: DataEntityMovieByImdbId) : HomeIntent()
}