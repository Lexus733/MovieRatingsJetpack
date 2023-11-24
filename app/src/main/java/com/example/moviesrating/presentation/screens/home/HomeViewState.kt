package com.example.moviesrating.presentation.screens.home

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviebyimdbid.Gen

sealed class HomeViewState {
    object Loading : HomeViewState()
    object NoItems : HomeViewState()

    data class Error(
        val code: Int?,
        val message: String?
    ) : HomeViewState()
    data class Display(
        val items: List<DataEntityMovieByImdbId>,
        val genMap: HashMap<Gen, List<DataEntityMovieByImdbId>>
    ) : HomeViewState()
}