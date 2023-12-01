package com.example.moviesrating.data.remote.api

import com.example.moviesrating.data.remote.model.actordetails.DataEntityActorDetails
import com.example.moviesrating.data.remote.model.moviecast.DataEntityMovieCast
import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("/movie/order/byPopularity/")
    suspend fun getListMoviesOrderByPopularity() : DataEntityMoviesByPopularity

    @GET("/movie/id/{imdbId}/")
    suspend fun getMovieByImdbId(@Path("imdbId") imdbId: String) : DataEntityMovieByImdbId

    @GET("/actor/id/{actorId}/")
    suspend fun getDetailsByActorId(@Path("actorId") actorId: String) : DataEntityActorDetails

    @GET("/movie/id/{imdbId}/cast/")
    suspend fun getListCastByMovieId(@Path("imdbId") imdbId: String) : DataEntityMovieCast
}