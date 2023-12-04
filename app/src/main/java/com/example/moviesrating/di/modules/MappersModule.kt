package com.example.moviesrating.di.modules

import com.example.moviesrating.domain.model.actordetails.EntityActorDetailsMapper
import com.example.moviesrating.domain.model.moviecast.EntityMovieCastMapper
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailMapper
import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularityMapper
import com.example.moviesrating.domain.model.search.EntityMovieSearchResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun entityMovieDetailMapper(): EntityMovieDetailMapper = EntityMovieDetailMapper()

    @Provides
    fun entityMovieCastMapper(): EntityMovieCastMapper = EntityMovieCastMapper()

    @Provides
    fun entityMoviesByPopularityMapper(): EntityMoviesByPopularityMapper = EntityMoviesByPopularityMapper()

    @Provides
    fun entityActorDetailsMapper(): EntityActorDetailsMapper = EntityActorDetailsMapper()

    @Provides
    fun entityMovieSearchResultMapper(): EntityMovieSearchResultMapper = EntityMovieSearchResultMapper()
}