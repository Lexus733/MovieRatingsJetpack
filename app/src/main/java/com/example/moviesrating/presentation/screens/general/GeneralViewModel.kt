package com.example.moviesrating.presentation.screens.general

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.di.modules.RetrofitApiModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class GeneralViewModel(
    @Inject private val repository: MovieApiRepository
) : ViewModel() {

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                // TODO мб на Hilt перейти потестить
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])
                // Create a SavedStateHandle for this ViewModel from extras
                val savedStateHandle = extras.createSavedStateHandle()

                val moshi = RetrofitApiModule.provideMoshi()
                val okhttp = RetrofitApiModule.provideOkHttp()
                val api = RetrofitApiModule.provideMovieApi(moshi, okhttp)
                val repository = RetrofitApiModule.provideRepository(api)

                return GeneralViewModel(repository) as T
            }
        }
    }

    private val _moviesState = MutableStateFlow(GeneralState())
    val movieState: StateFlow<GeneralState> = _moviesState.asStateFlow()

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

