package com.example.moviesrating.domain.model.moviedetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntityGen(
    val genre: String,
    val id: Int
) : Parcelable