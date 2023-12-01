package com.example.moviesrating.domain.model.moviedetail

import android.os.Parcelable
import com.example.moviesrating.domain.model.actordetails.EntityActorDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntityMovieDetail(
    val imdb_id: String,
    val banner: String,
    val description: String,
    val gen: List<EntityGen>,
    val image_url: String?,
    val movie_length: String,
    val rating: Double,
    val title: String,
    val year: Int,
    val castList: List<EntityActorDetails>?
) : Parcelable
