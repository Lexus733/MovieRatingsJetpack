package com.example.moviesrating.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.domain.interactor.HomeScreenInteractor
import com.example.moviesrating.utils.IntentHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val interactor: HomeScreenInteractor
) : ViewModel(), IntentHandler<HomeIntent> {

    private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeViewState: StateFlow<HomeViewState> = _homeViewState.asStateFlow()

    override fun obtainIntent(intent: HomeIntent) {
        when (val currentState = _homeViewState.value) {
            is HomeViewState.Error -> reduce(intent, currentState)
            is HomeViewState.Loading -> reduce(intent, currentState)
            is HomeViewState.NoItems -> reduce(intent, currentState)
            is HomeViewState.Display -> reduce(intent, currentState)
        }
    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.Display) {
        when (intent) {
            else -> Log.d("HomeViewState.Display", "Invalid $intent for state $currentState")
        }
    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.NoItems) {
        when (intent) {
            else -> Log.d("HomeViewState.NoItems", "Invalid $intent for state $currentState")
        }
    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.Loading) {
        when (intent) {
            is HomeIntent.EnterScreen -> getMovieList()
            else -> Log.d("HomeViewState.Loading", "Invalid $intent for state $currentState")
        }
    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.Error) {
        when (intent) {
            else -> Log.d("HomeViewState.Error", "Invalid $intent for state $currentState")
        }
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _homeViewState.update { HomeViewState.Loading }

            val result = interactor.getData()

            _homeViewState.update {
                if (result.list.isNotEmpty()) {
                    HomeViewState.Display(items = result.list, result.gens)
                } else {
                    HomeViewState.NoItems
                }
            }
        }
    }
}
