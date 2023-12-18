package com.example.moviesrating.presentation.screens.detail

import androidx.lifecycle.ViewModel
import com.example.moviesrating.utils.IntentHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor() : ViewModel(), IntentHandler<MovieDetailIntent> {

    private val _movieDetailViewState =
        MutableStateFlow<MovieDetailViewState>(MovieDetailViewState.Loading)
    val movieDetailViewState: StateFlow<MovieDetailViewState> = _movieDetailViewState.asStateFlow()

    override fun obtainIntent(intent: MovieDetailIntent) {
        if (intent is MovieDetailIntent.EnterScreen) {
            _movieDetailViewState.value = if (intent.movie != null) {
                MovieDetailViewState.Display(intent.movie)
            } else {
                MovieDetailViewState.Error
            }
        }
    }
}
