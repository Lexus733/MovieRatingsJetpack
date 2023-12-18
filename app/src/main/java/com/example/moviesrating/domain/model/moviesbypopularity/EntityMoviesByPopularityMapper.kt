package com.example.moviesrating.domain.model.moviesbypopularity

import com.example.moviesrating.data.remote.model.moviesbypopularity.DataEntityMoviesByPopularity

const val POPULARITY_SIZE = 10

class EntityMoviesByPopularityMapper {

    fun adapt(dataEntityMoviesByPopularity: DataEntityMoviesByPopularity): EntityMoviesByPopularity {
        val results = dataEntityMoviesByPopularity.results?.take(POPULARITY_SIZE)
        val list = arrayListOf<String>()
        results?.map {
            list.add(it.imdb_id!!)
        }
        return EntityMoviesByPopularity(ids = list)
    }
}
