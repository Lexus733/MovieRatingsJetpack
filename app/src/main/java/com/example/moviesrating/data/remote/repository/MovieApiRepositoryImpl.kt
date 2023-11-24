package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.utils.retrofit.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val POPULARITY_SIZE = 10

class MovieApiRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieApiRepository {

    override suspend fun getListMoviesOrderByPopularity(): DataEntityMoviesByPopularity =
        withContext(ioDispatcher) {
            val data = api.getListMoviesOrderByPopularity()
            val results = data.results?.take(POPULARITY_SIZE)
            return@withContext data.copy(results = results)
        }

    override suspend fun getListMoviesOrderByPopularityNetworkResult(): NetworkResult<DataEntityMoviesByPopularity> =
        withContext(ioDispatcher) {
            when (val data = api.getListMoviesOrderByPopularityNetworkResult()) {
                is NetworkResult.Success -> {
                    data.data = data.data.copy(
                        results = data.data.results?.take(POPULARITY_SIZE)!!
                    )
                    return@withContext data
                }

                else -> {
                    return@withContext data
                }
            }
        }

    override suspend fun getMovieByImdbIdNetworkResult(imdbId: String): NetworkResult<DataEntityMovieByImdbId> =
        withContext(ioDispatcher) {
            api.getMovieByImdbIdNetworkResult(imdbId)
        }


    override suspend fun getMovieByImdbId(imdbId: String): DataEntityMovieByImdbId =
        withContext(ioDispatcher) {
            api.getMovieByImdbId(imdbId)
        }
}