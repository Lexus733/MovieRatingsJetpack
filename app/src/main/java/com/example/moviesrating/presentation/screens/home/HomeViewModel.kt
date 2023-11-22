package com.example.moviesrating.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieApiRepository
) : ViewModel() {

    private val _moviesState = MutableStateFlow(HomeState())
    val movieState: StateFlow<HomeState> = _moviesState.asStateFlow()

    fun getMovieList() {
        viewModelScope.launch {
            repository.getListMoviesOrderByPopularity().collect { response ->
                _moviesState.update {
                    it.copy(info = response.toString())
                }
            }
        }
    }
}

