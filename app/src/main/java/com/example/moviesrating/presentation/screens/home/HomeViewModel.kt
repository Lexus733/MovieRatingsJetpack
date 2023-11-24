package com.example.moviesrating.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviebyimdbid.Gen
import com.example.moviesrating.domain.usecase.GetPopularFilmsDataNetworkResultUseCaseImpl
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
) : ViewModel() {

    private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeViewState: StateFlow<HomeViewState> = _homeViewState.asStateFlow()

    init {
        getMovieList()
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

    private fun getGenMap(success: List<DataEntityMovieByImdbId>): HashMap<Gen, List<DataEntityMovieByImdbId>> {
        val genSet = mutableSetOf<Gen>()
        val hashMap = hashMapOf<Gen, List<DataEntityMovieByImdbId>>()

        success.map { genSet.addAll(it.results.gen) }

        genSet.map { gen ->
            hashMap.put(gen, success.filter { it.results.gen.contains(gen) })
        }

        return hashMap
    }
}

