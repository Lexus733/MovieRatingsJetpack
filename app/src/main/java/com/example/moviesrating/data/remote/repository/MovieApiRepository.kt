package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularity
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.model.search.EntityMovieSearchResult

interface MovieApiRepository {

    suspend fun getListMoviesOrderByPopularity(): EntityMoviesByPopularity
    suspend fun getMovieByImdbId(imdbId: String): EntityMovieDetail
    suspend fun getListByMovieTitle(title: String) : List<EntityMovieSearchResult>
}