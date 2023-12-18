package com.example.moviesrating.domain.model.moviedetail

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviebyimdbid.Gen

class EntityMovieDetailMapper {

    fun adapt(dataEntityMovieByImdbId: DataEntityMovieByImdbId): EntityMovieDetail {
        return EntityMovieDetail(
            imdb_id = dataEntityMovieByImdbId.results.imdb_id,
            banner = dataEntityMovieByImdbId.results.banner,
            description = dataEntityMovieByImdbId.results.description,
            gen = adaptGen(dataEntityMovieByImdbId.results.gen),
            image_url = dataEntityMovieByImdbId.results.image_url,
            movie_length = "${dataEntityMovieByImdbId.results.movie_length} minutes",
            rating = dataEntityMovieByImdbId.results.rating,
            title = dataEntityMovieByImdbId.results.title,
            year = dataEntityMovieByImdbId.results.year,
            castList = listOf()
        )
    }

    private fun adaptGen(gen: List<Gen>): List<EntityGen> {
        val list = mutableListOf<EntityGen>()

        gen.map {
            list.add(
                EntityGen(
                    genre = it.genre,
                    id = it.id
                )
            )
        }

        return list
    }
}
