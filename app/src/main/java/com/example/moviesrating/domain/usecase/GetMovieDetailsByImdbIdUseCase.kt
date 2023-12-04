package com.example.moviesrating.domain.usecase

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

interface GetMovieDetailsByImdbIdUseCase {

    suspend fun getDetails(imdbId: String) : EntityMovieDetail
}