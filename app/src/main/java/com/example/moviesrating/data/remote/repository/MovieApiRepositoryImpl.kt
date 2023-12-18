package com.example.moviesrating.data.remote.repository

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailMapper
import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularity
import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularityMapper
import com.example.moviesrating.domain.model.search.EntityMovieSearchResult
import com.example.moviesrating.domain.model.search.EntityMovieSearchResultMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    private val api: MovieApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val entityMovieDetailMapper: EntityMovieDetailMapper,
    private val entityMoviesByPopularityMapper: EntityMoviesByPopularityMapper,
    private val entityMoviesSearchMapper: EntityMovieSearchResultMapper
) : MovieApiRepository {

    override suspend fun getListMoviesOrderByPopularity(): EntityMoviesByPopularity =
        withContext(ioDispatcher) {
            entityMoviesByPopularityMapper.adapt(api.getListMoviesOrderByPopularity())
        }

    override suspend fun getMovieByImdbId(imdbId: String): EntityMovieDetail =
        withContext(ioDispatcher) {
            entityMovieDetailMapper.adapt(api.getMovieByImdbId(imdbId))
        }

    override suspend fun getListByMovieTitle(title: String): List<EntityMovieSearchResult> =
        withContext(ioDispatcher) {
            entityMoviesSearchMapper.adapt(api.getListByMovieTitle(title))
        }
}
