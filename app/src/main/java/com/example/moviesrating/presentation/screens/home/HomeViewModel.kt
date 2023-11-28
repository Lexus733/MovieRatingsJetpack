package com.example.moviesrating.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.domain.model.moviedetail.EntityGen
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.usecase.GetPopularFilmsDataNetworkResultUseCaseImpl
import com.example.moviesrating.utils.IntentHandler
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
    private val useCase: GetPopularFilmsDataNetworkResultUseCaseImpl
) : ViewModel(), IntentHandler<HomeIntent> {

    private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeViewState: StateFlow<HomeViewState> = _homeViewState.asStateFlow()

    override fun obtainIntent(intent: HomeIntent) {
        when(val currentState = _homeViewState.value) {
            is HomeViewState.Error -> reduce(intent, currentState)
            is HomeViewState.Loading -> reduce(intent, currentState)
            is HomeViewState.NoItems -> reduce(intent, currentState)
            is HomeViewState.Display -> reduce(intent, currentState)
        }
    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.Display) {

    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.NoItems) {

    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.Loading) {
        when(intent) {
            is HomeIntent.EnterScreen -> getMovieList()
            else -> Log.d("HomeViewState.Loading","Invalid $intent for state $currentState")
        }
    }

    private fun reduce(intent: HomeIntent, currentState: HomeViewState.Error) {

    }

    private fun getMovieList() {
        viewModelScope.launch {

            _homeViewState.update { HomeViewState.Loading }

            useCase.getPopularMovies()
                .onSuccess { success ->
                    _homeViewState.update {
                        if (success.isNotEmpty())
                            HomeViewState.Display(items = success, getGenMap(success = success))
                        else HomeViewState.NoItems
                    }
                }.onError { code, message ->
                    _homeViewState.update { HomeViewState.Error(code, message) }
                }.onException {
                    _homeViewState.update { HomeViewState.Error(null, it.toString()) }
                }
        }
    }

    private fun getGenMap(success: List<EntityMovieDetail>): HashMap<EntityGen, List<EntityMovieDetail>> {
        val genSet = mutableSetOf<EntityGen>()
        val hashMap = hashMapOf<EntityGen, List<EntityMovieDetail>>()

        success.map { genSet.addAll(it.gen) }

        genSet.map { gen ->
            hashMap.put(gen, success.filter { it.gen.contains(gen) })
        }

        return hashMap
    }

}

