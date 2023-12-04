package com.example.moviesrating.domain.model.search

import com.example.moviesrating.data.remote.model.search.DataEntityMovieSearchResult

const val ITEMS = 5

class EntityMovieSearchResultMapper {

    fun adapt(dataEntityMovieSearchResult: DataEntityMovieSearchResult) : List<EntityMovieSearchResult> {
        val list = arrayListOf<EntityMovieSearchResult>()
        dataEntityMovieSearchResult.results.take(ITEMS).map {
            list.add(EntityMovieSearchResult(imdb_id = it.imdb_id, title = it.title))
        }
        return list
    }
}