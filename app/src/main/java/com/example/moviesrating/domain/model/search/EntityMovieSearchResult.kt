package com.example.moviesrating.domain.model.search

import androidx.compose.runtime.Immutable

@Immutable
data class EntityMovieSearchResult(
    val imdb_id: String,
    val title: String
)
