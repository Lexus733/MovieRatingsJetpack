package com.example.moviesrating.data.remote.repository

import android.util.Log
import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    var api: MovieApi
) : MovieApiRepository {

    override suspend fun getListMoviesOrderByPopularity(): Flow<DataEntityMoviesByPopularity> =
        flow {
            try {
                emit(api.getListMoviesOrderByPopularity())
            } catch (e: Exception) {
                Log.d("Tag", e.toString())
            }
        }.flowOn(Dispatchers.IO)
}