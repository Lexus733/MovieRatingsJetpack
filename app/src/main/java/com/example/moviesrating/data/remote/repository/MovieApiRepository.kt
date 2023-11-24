package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import com.example.moviesrating.utils.retrofit.NetworkResult

interface MovieApiRepository {

   suspend fun getListMoviesOrderByPopularity() : DataEntityMoviesByPopularity
   suspend fun getMovieByImdbId(imdbId: String) : DataEntityMovieByImdbId

   suspend fun getListMoviesOrderByPopularityNetworkResult() : NetworkResult<DataEntityMoviesByPopularity>
   suspend fun getMovieByImdbIdNetworkResult(imdbId: String) : NetworkResult<DataEntityMovieByImdbId>
}