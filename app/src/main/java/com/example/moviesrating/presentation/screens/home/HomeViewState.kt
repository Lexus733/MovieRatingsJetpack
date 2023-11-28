package com.example.moviesrating.presentation.screens.home

import com.example.moviesrating.domain.model.moviedetail.EntityGen
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

sealed class HomeViewState {
    object Loading : HomeViewState()
    object NoItems : HomeViewState()

    data class Error(
        val code: Int?,
        val message: String?
    ) : HomeViewState()
    data class Display(
        val items: List<EntityMovieDetail>,
        val genMap: HashMap<EntityGen, List<EntityMovieDetail>>
    ) : HomeViewState()
}