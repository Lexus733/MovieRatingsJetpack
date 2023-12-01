package com.example.moviesrating.domain.model.moviecast

import com.example.moviesrating.data.remote.model.moviecast.DataEntityMovieCast

const val TAKE_ITEMS = 5

class EntityMovieCastMapper {

    fun adapt(dataEntity: DataEntityMovieCast): EntityMovieCast {
        val list = arrayListOf<String>()
        dataEntity.results.roles.take(TAKE_ITEMS).map {
            list.add(it.actor?.imdb_id ?: "")
        }
        return EntityMovieCast(list)
    }
}