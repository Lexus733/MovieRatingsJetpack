package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetMovieDetailsByImdbIdUseCaseImpl @Inject constructor(
    private val moviesApiRepository: MovieApiRepository
) : GetMovieDetailsByImdbIdUseCase {

    override suspend fun getDetails(imdbId: String): EntityMovieDetail = coroutineScope {
        return@coroutineScope moviesApiRepository.getMovieByImdbId(imdbId)
    }
}
