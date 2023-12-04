package com.example.moviesrating.domain.usecase

import com.example.moviesrating.domain.model.search.EntityMovieSearchResult

interface GetMovieListByTitleUseCase {
    suspend fun getMoviesListByTitle(title: String) : List<EntityMovieSearchResult>
}