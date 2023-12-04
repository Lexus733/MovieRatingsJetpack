package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.domain.model.search.EntityMovieSearchResult
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetMovieListByTitleUseCaseImpl @Inject constructor(
    private val movieApiRepository: MovieApiRepository
) : GetMovieListByTitleUseCase {

    override suspend fun getMoviesListByTitle(title: String): List<EntityMovieSearchResult> =
        coroutineScope {
            return@coroutineScope movieApiRepository.getListByMovieTitle(title)
        }
}