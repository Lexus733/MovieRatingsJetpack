package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPopularFilmsDataUseCaseImpl @Inject constructor(
    private val movieApiRepository: MovieApiRepository
) : GetPopularFilmsDataUseCase {

    override suspend fun getPopularMovies(): List<EntityMovieDetail> = coroutineScope {
        val movieIds = async { movieApiRepository.getListMoviesOrderByPopularity() }.await()
        val list = movieIds.ids.map { result ->
            async {
                movieApiRepository.getMovieByImdbId(result)
            }.await()
        }
        return@coroutineScope list
    }
}