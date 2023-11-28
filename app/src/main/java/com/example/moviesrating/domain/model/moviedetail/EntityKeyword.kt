package com.example.moviesrating.domain.model.moviedetail

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EntityKeyword(
    val id: Int,
    val keyword: String
) : Parcelable