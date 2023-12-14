package com.example.moviesrating.domain.model.actordetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class EntityActorDetails(
    val image_url: String?,
    val name: String,
) : Parcelable
