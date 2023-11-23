package com.example.moviesrating.data.remote.api

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import com.example.moviesrating.utils.retrofit.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/movie/order/byPopularity/")
    suspend fun getListMoviesOrderByPopularityFlow() : NetworkResult<DataEntityMoviesByPopularity>

    @GET("/movie/order/byPopularity/")
    suspend fun getListMoviesOrderByPopularity() : DataEntityMoviesByPopularity

    @GET("/movie/id/{imdbId}/")
    suspend fun getMovieByImdbId(@Path("imdbId") imdbId: String) : DataEntityMovieByImdbId
}