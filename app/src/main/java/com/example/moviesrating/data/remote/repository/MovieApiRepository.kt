package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import com.example.moviesrating.utils.retrofit.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MovieApiRepository {
   suspend fun getListMoviesOrderByPopularityFlow() : Flow<NetworkResult<DataEntityMoviesByPopularity>>
   suspend fun getListMoviesOrderByPopularity() : DataEntityMoviesByPopularity
   suspend fun getMovieByImdbId(imdbId: String) : DataEntityMovieByImdbId
}