package com.example.moviesrating.presentation.screens.search

sealed class SearchViewIntent() {

    object EnterScreen : SearchViewIntent()
    data class StartSearching(val searchText: String) : SearchViewIntent()

}