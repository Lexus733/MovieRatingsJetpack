package com.example.moviesrating.domain.usecase

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail

interface GetPopularFilmsDataUseCase {
    suspend fun getPopularMovies(): List<EntityMovieDetail>
}
