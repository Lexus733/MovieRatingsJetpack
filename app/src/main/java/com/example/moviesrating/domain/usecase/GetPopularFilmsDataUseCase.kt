package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPopularFilmsDataUseCase @Inject constructor(
    private val movieApiRepository: MovieApiRepository
) {
    suspend fun getPopularMovies(): List<DataEntityMovieByImdbId> {
        return coroutineScope {
            val movieIds = async { movieApiRepository.getListMoviesOrderByPopularity() }.await()
            val list = movieIds.results?.map {result ->
                async {
                    movieApiRepository.getMovieByImdbId(result.imdb_id!!)
                }.await()
            }
            return@coroutineScope list!!
        }
    }
}