package com.example.moviesrating.presentation.screens.detail

import androidx.lifecycle.ViewModel
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.utils.IntentHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
) : ViewModel(), IntentHandler<MovieDetailIntent> {

    private val _movieDetailViewState =
        MutableStateFlow<MovieDetailViewState>(MovieDetailViewState.Loading)
    val movieDetailViewState: StateFlow<MovieDetailViewState> = _movieDetailViewState.asStateFlow()

    fun updateViewModel(entityMovieDetail: EntityMovieDetail) {
        _movieDetailViewState.value = MovieDetailViewState.Display(entityMovieDetail)
    }

    override fun obtainIntent(intent: MovieDetailIntent) {
        when(val currentState = _movieDetailViewState.value) {
            is MovieDetailViewState.Loading -> reduce(intent, currentState)
            is MovieDetailViewState.Display -> reduce(intent, currentState)
        }
    }

    private fun reduce(intent: MovieDetailIntent, currentState: MovieDetailViewState.Loading) {

    }

    private fun reduce(intent: MovieDetailIntent, currentState: MovieDetailViewState.Display) {

    }
}