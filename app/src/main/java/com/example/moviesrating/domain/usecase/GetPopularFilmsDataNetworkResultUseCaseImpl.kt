package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviesbypopularity.Result
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.utils.retrofit.NetworkResult
import com.example.moviesrating.utils.retrofit.onSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPopularFilmsDataNetworkResultUseCaseImpl @Inject constructor(
    private val movieApiRepository: MovieApiRepository
) : GetPopularFilmsDataUseCase<NetworkResult<List<DataEntityMovieByImdbId>>> {

    override suspend fun getPopularMovies(): NetworkResult<List<DataEntityMovieByImdbId>> {
        return coroutineScope {
            val movieIds =
                async { movieApiRepository.getListMoviesOrderByPopularityNetworkResult() }.await()
            val list: ArrayList<DataEntityMovieByImdbId> = arrayListOf()
            movieIds.onSuccess { dataEntity ->
                dataEntity.results?.map { result: Result ->
                    async { movieApiRepository.getMovieByImdbIdNetworkResult(result.imdb_id!!) }
                        .await()
                        .onSuccess { list.add(it) }
                }
            }

            return@coroutineScope NetworkResult.Success(list)
        }
    }
}