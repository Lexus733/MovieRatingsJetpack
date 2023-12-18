package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.domain.model.moviecast.EntityMovieCast
import com.example.moviesrating.domain.model.moviecast.EntityMovieCastMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("SwallowedException", "TooGenericExceptionCaught")
class CastApiRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val entityMovieCastMapper: EntityMovieCastMapper
) : CastApiRepository {

    override suspend fun getCastListByMovieId(imdbId: String): EntityMovieCast =
        withContext(ioDispatcher) {
            try {
                return@withContext entityMovieCastMapper.adapt(api.getListCastByMovieId(imdbId))
            } catch (exc: Exception) {
                return@withContext EntityMovieCast(listOf())
            }
        }
}
