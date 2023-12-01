package com.example.moviesrating.domain.usecase

import com.example.moviesrating.domain.model.moviecast.EntityMovieCast

interface GetMovieCastByIdUseCase {

    suspend fun getMovieCast(imdbId: String) : EntityMovieCast
}