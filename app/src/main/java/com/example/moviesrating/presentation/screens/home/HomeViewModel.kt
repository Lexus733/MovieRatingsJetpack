package com.example.moviesrating.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.domain.usecase.GetPopularFilmsDataUseCase
import com.example.moviesrating.utils.retrofit.onError
import com.example.moviesrating.utils.retrofit.onException
import com.example.moviesrating.utils.retrofit.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetPopularFilmsDataUseCase
) : ViewModel() {

    private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeViewState: StateFlow<HomeViewState> = _homeViewState.asStateFlow()

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _homeViewState.update { HomeViewState.Loading }
            _homeViewState.update {
                HomeViewState.Display(useCase.getPopularMovies())
            }
        }
    }
}

