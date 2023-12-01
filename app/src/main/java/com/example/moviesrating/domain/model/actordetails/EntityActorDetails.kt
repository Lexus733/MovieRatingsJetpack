package com.example.moviesrating.domain.model.actordetails

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntityActorDetails(
    val image_url: String?,
    val name: String,
) : Parcelable
