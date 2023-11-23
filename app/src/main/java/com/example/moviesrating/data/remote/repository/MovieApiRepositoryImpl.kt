package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.utils.retrofit.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val POPULARITY_SIZE = 5

class MovieApiRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieApiRepository {

    override suspend fun getListMoviesOrderByPopularityFlow(): Flow<NetworkResult<DataEntityMoviesByPopularity>> =
        flow {
            emit(api.getListMoviesOrderByPopularityFlow())
        }.flowOn(ioDispatcher)

    override suspend fun getListMoviesOrderByPopularity(): DataEntityMoviesByPopularity =
        withContext(ioDispatcher) {
            val data = api.getListMoviesOrderByPopularity()
            val results = data.results?.take(POPULARITY_SIZE)
            return@withContext data.copy(results = results)
        }

    override suspend fun getMovieByImdbId(imdbId: String): DataEntityMovieByImdbId =
        withContext(ioDispatcher) {
            api.getMovieByImdbId(imdbId)
        }
}