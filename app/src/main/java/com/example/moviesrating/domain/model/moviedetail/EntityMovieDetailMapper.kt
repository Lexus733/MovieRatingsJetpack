package com.example.moviesrating.domain.model.moviedetail

import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.data.remote.model.moviebyimdbid.Gen
import com.example.moviesrating.data.remote.model.moviebyimdbid.Keyword


class EntityMovieDetailMapper {

    fun adapt(dataEntityMovieByImdbId: DataEntityMovieByImdbId): EntityMovieDetail {
        return EntityMovieDetail(
            banner = dataEntityMovieByImdbId.results.banner,
            content_rating = dataEntityMovieByImdbId.results.content_rating,
            created_at = dataEntityMovieByImdbId.results.created_at,
            description = dataEntityMovieByImdbId.results.description,
            gen = adaptGen(dataEntityMovieByImdbId.results.gen),
            image_url = dataEntityMovieByImdbId.results.image_url,
            imdb_id = dataEntityMovieByImdbId.results.imdb_id,
            keywords = adaptKeywords(dataEntityMovieByImdbId.results.keywords),
            movie_length = dataEntityMovieByImdbId.results.movie_length,
            plot = dataEntityMovieByImdbId.results.plot,
            popularity = dataEntityMovieByImdbId.results.popularity,
            rating = dataEntityMovieByImdbId.results.rating,
            release = dataEntityMovieByImdbId.results.release,
            title = dataEntityMovieByImdbId.results.title,
            trailer = dataEntityMovieByImdbId.results.trailer,
            type = dataEntityMovieByImdbId.results.type,
            year = dataEntityMovieByImdbId.results.year
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

    private fun adaptKeywords(keywords: List<Keyword>): List<EntityKeyword> {
        val list = mutableListOf<EntityKeyword>()

        keywords.map {
            list.add(
                EntityKeyword(
                    id = it.id,
                    keyword = it.keyword
                )
            )
        }

        return list
    }
}