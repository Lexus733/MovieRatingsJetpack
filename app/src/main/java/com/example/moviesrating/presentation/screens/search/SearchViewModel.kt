package com.example.moviesrating.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrating.domain.interactor.SearchScreenInteractor
import com.example.moviesrating.utils.IntentHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val interactor: SearchScreenInteractor
) : ViewModel(), IntentHandler<SearchViewIntent> {

    private val _viewState = MutableStateFlow<SearchViewState>(SearchViewState.Loading)
    val viewState: StateFlow<SearchViewState> = _viewState.asStateFlow()

    private val _searchTextState = MutableStateFlow("")
    val searchTextState: StateFlow<String> = _searchTextState.asStateFlow()

    init {
        viewModelScope.launch {
            _searchTextState
                .debounce(1000L)
                .collectLatest {
                    if (it.isNotEmpty()) {
                        _viewState.update {
                            SearchViewState.Loading
                        }

                        val result = interactor.getDataBySearchText(_searchTextState.value)

                        _viewState.update {
                            if (result.isNotEmpty()) SearchViewState.Display(result)
                            else SearchViewState.NoItems
                        }
                    } else {
                        _viewState.update {
                            SearchViewState.NoItems
                        }
                    }
                }
        }
    }

    fun updateSearchText(searchText: String) {
        _searchTextState.update { searchText }
    }

    override fun obtainIntent(intent: SearchViewIntent) {
        when (val currentState = _viewState.value) {
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