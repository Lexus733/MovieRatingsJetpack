package com.example.moviesrating.domain.usecase

import com.example.moviesrating.data.remote.model.moviesbypopularity.Result
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailMapper
import com.example.moviesrating.utils.retrofit.NetworkResult
import com.example.moviesrating.utils.retrofit.onSuccess
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPopularFilmsDataNetworkResultUseCaseImpl @Inject constructor(
    private val movieApiRepository: MovieApiRepository,
    private val entityMovieDetailMapper: EntityMovieDetailMapper
) : GetPopularFilmsDataUseCase<NetworkResult<List<EntityMovieDetail>>> {

    override suspend fun getPopularMovies(): NetworkResult<List<EntityMovieDetail>> {
        return coroutineScope {
            val movieIds =
                async { movieApiRepository.getListMoviesOrderByPopularityNetworkResult() }.await()
            val list: ArrayList<EntityMovieDetail> = arrayListOf()
            movieIds.onSuccess { dataEntity ->
                dataEntity.results?.map { result: Result ->
                    async { movieApiRepository.getMovieByImdbIdNetworkResult(result.imdb_id!!) }
                        .await()
                        .onSuccess { list.add(entityMovieDetailMapper.adapt(it)) }
                }
            }

            return@coroutineScope NetworkResult.Success(list)
        }
    }
}