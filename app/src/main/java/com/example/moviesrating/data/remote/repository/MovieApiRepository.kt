package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularity
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

interface MovieApiRepository {

    suspend fun getListMoviesOrderByPopularity(): EntityMoviesByPopularity
    suspend fun getMovieByImdbId(imdbId: String): EntityMovieDetail
}