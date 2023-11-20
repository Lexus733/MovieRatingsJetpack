package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieApiRepository {

    override suspend fun getListMoviesOrderByPopularity(): Flow<DataEntityMoviesByPopularity> =
        flow {
            emit(api.getListMoviesOrderByPopularity())
        }.flowOn(Dispatchers.IO)
}