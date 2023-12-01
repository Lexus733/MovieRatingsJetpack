package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailMapper
import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularity
import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val entityMovieDetailMapper: EntityMovieDetailMapper,
    private val entityMoviesByPopularityMapper: EntityMoviesByPopularityMapper
) : MovieApiRepository {

    override suspend fun getListMoviesOrderByPopularity(): EntityMoviesByPopularity =
        withContext(ioDispatcher) {
            entityMoviesByPopularityMapper.adapt(api.getListMoviesOrderByPopularity())
        }

    override suspend fun getMovieByImdbId(imdbId: String): EntityMovieDetail =
        withContext(ioDispatcher) {
            entityMovieDetailMapper.adapt(api.getMovieByImdbId(imdbId))
        }
}