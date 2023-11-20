package com.example.moviesrating.data.remote.api

import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import retrofit2.http.GET

interface MovieApi {

    @GET("/movie/order/byPopularity/")
    suspend fun getListMoviesOrderByPopularity() : DataEntityMoviesByPopularity
}