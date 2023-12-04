package com.example.moviesrating.di.modules

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.repository.ActorDetailsApiRepository
import com.example.moviesrating.data.remote.repository.ActorDetailsApiRepositoryImpl
import com.example.moviesrating.data.remote.repository.CastApiRepository
import com.example.moviesrating.data.remote.repository.CastApiRepositoryImpl
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.data.remote.repository.MovieApiRepositoryImpl
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.domain.model.actordetails.EntityActorDetailsMapper
import com.example.moviesrating.domain.model.moviecast.EntityMovieCastMapper
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetailMapper
import com.example.moviesrating.domain.model.moviesbypopularity.EntityMoviesByPopularityMapper
import com.example.moviesrating.domain.model.search.EntityMovieSearchResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: MovieApi,
        @IoDispatcher io: CoroutineDispatcher,
        entityMovieDetailMapper: EntityMovieDetailMapper,
        entityMoviesByPopularityMapper: EntityMoviesByPopularityMapper,
        entityMovieSearchResultMapper: EntityMovieSearchResultMapper
    ): MovieApiRepository =
        MovieApiRepositoryImpl(api, io, entityMovieDetailMapper, entityMoviesByPopularityMapper, entityMovieSearchResultMapper)

    @Singleton
    @Provides
    fun provideCastRepository(
        api: MovieApi,
        @IoDispatcher io: CoroutineDispatcher,
        entityMovieCastMapper: EntityMovieCastMapper
    ): CastApiRepository =
        CastApiRepositoryImpl(api, io, entityMovieCastMapper)

    @Singleton
    @Provides
    fun provideActorDetailsRepository(
        api: MovieApi,
        @IoDispatcher io: CoroutineDispatcher,
        entityActorDetailsMapper: EntityActorDetailsMapper
    ): ActorDetailsApiRepository =
        ActorDetailsApiRepositoryImpl(api, io, entityActorDetailsMapper)
}