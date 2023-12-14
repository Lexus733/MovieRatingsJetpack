package com.example.moviesrating.domain.model.moviedetail

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.moviesrating.domain.model.actordetails.EntityActorDetails
import kotlinx.parcelize.Parcelize

@Immutable
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

class EntityMovieDetailPreview : PreviewParameterProvider<EntityMovieDetail> {
    override val values: Sequence<EntityMovieDetail>
        get() = sequenceOf(
            EntityMovieDetail(
                imdb_id = "imdb_id",
                banner = "banner",
                description = "description",
                gen = listOf(),
                image_url = "image_url",
                movie_length = "movie_length",
                rating = 0.1,
                title = "title",
                year = 1990,
                castList = listOf()
            )
        )
}
