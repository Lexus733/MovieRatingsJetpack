package com.example.moviesrating.domain.model.moviedetail

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class EntityKeyword(
    val id: Int,
    val keyword: String
) : Parcelable
