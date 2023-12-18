package com.example.moviesrating.domain.interactor

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.domain.usecase.GetMovieDetailsByImdbIdUseCase
import com.example.moviesrating.domain.usecase.GetMovieListByTitleUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SearchScreenInteractor @Inject constructor(
    private val getMovieListByTitleUseCase: GetMovieListByTitleUseCase,
    private val getMovieDetailsByImdbIdUseCase: GetMovieDetailsByImdbIdUseCase
) {

    suspend fun getDataBySearchText(searchText: String): ImmutableList<EntityMovieDetail> = coroutineScope {
        val searchResult = getMovieListByTitleUseCase.getMoviesListByTitle(searchText)
        val result = arrayListOf<Deferred<EntityMovieDetail>>()
        searchResult.map { search ->
            result.add(
                async { getMovieDetailsByImdbIdUseCase.getDetails(search.imdb_id) }
            )
        }
        return@coroutineScope result.awaitAll().toImmutableList()
    }
}
