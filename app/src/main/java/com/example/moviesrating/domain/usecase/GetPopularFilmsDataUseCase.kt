package com.example.moviesrating.domain.usecase

interface GetPopularFilmsDataUseCase<T> {
    suspend fun getPopularMovies() : T
}