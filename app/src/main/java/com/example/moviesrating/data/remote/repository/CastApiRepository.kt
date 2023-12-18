package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.domain.model.moviecast.EntityMovieCast

interface CastApiRepository {

    suspend fun getCastListByMovieId(imdbId: String): EntityMovieCast
}
