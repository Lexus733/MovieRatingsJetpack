package com.example.moviesrating.di.modules

import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.data.remote.repository.MovieApiRepositoryImpl
import com.example.moviesrating.di.IoDispatcher
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
    fun provideRepository(
        api: MovieApi,
        @IoDispatcher io: CoroutineDispatcher
    ): MovieApiRepository = MovieApiRepositoryImpl(api = api, io)
}