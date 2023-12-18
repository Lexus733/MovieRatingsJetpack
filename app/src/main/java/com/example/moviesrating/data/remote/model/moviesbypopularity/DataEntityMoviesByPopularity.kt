package com.example.moviesrating.data.remote.model.moviesbypopularity

data class DataEntityMoviesByPopularity(
    val count: Int?,
    val links: Links?,
    val results: List<Result>?
)
