package com.example.moviesrating.di.modules

import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun movieDetailsMapper(): EntityMovieDetailMapper = EntityMovieDetailMapper()
}