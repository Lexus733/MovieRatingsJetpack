package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.repository.CastApiRepository
import com.example.moviesrating.domain.model.moviecast.EntityMovieCast
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetMovieCastByIdUseCaseImpl @Inject constructor(
    private val castApiRepository: CastApiRepository
) : GetMovieCastByIdUseCase {
    override suspend fun getMovieCast(imdbId: String): EntityMovieCast = coroutineScope {
        castApiRepository.getCastListByMovieId(imdbId)
    }
}