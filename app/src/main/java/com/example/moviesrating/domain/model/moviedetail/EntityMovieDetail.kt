package com.example.moviesrating.domain.model.moviedetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class EntityMovieDetail(
    val banner: String,
    val content_rating: String,
    val created_at: String,
    val description: String,
    val gen: List<EntityGen>,
    val image_url: String,
    val imdb_id: String,
    val keywords: List<EntityKeyword>,
    val movie_length: Int,
    val plot: String,
    val popularity: Int,
    val rating: Double,
    val release: String,
    val title: String,
    val trailer: String,
    val type: String,
    val year: Int
) : Parcelable
