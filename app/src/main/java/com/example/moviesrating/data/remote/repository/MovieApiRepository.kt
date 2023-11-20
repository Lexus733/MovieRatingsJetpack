package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import kotlinx.coroutines.flow.Flow

interface MovieApiRepository {
   suspend fun getListMoviesOrderByPopularity() : Flow<DataEntityMoviesByPopularity>
}