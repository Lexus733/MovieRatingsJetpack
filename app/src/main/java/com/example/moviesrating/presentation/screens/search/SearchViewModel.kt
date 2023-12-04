package com.example.moviesrating.presentation.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.domain.interactor.SearchScreenInteractor
import com.example.moviesrating.utils.IntentHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: SearchScreenInteractor
) : ViewModel(), IntentHandler<SearchViewIntent> {

    var searchText by mutableStateOf("")
        private set

    private val _searchViewState = MutableStateFlow<SearchViewState>(SearchViewState.Loading)
    val searchViewState: StateFlow<SearchViewState> = _searchViewState.asStateFlow()

    fun updateSearchText(searchText: String) {
        this.searchText = searchText
    }

    fun getDataBySearchText() {
        viewModelScope.launch {

            _searchViewState.update {
                SearchViewState.Loading
            }

            val result = interactor.getDataBySearchText(searchText)

            _searchViewState.update {
                if (result.isNotEmpty()) SearchViewState.Display(result)
                else SearchViewState.NoItems
            }
        }
    }

    override fun obtainIntent(intent: SearchViewIntent) {
        when (val currentState = _searchViewState.value) {
            is SearchViewState.Loading -> reduce(intent, currentState)
            is SearchViewState.NoItems -> reduce(intent, currentState)
            is SearchViewState.Display -> reduce(intent, currentState)
        }
    }

    private fun reduce(intent: SearchViewIntent, currentState: SearchViewState.Display) {

    }

    private fun reduce(intent: SearchViewIntent, currentState: SearchViewState.NoItems) {

    }

    private fun reduce(intent: SearchViewIntent, currentState: SearchViewState.Loading) {
    }
}