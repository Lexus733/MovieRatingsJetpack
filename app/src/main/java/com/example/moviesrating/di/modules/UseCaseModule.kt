package com.example.moviesrating.di.modules

import com.example.moviesrating.data.remote.repository.ActorDetailsApiRepository
import com.example.moviesrating.data.remote.repository.CastApiRepository
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.domain.usecase.GetActorDetailsByIdUseCase
import com.example.moviesrating.domain.usecase.GetActorDetailsByIdUseCaseImpl
import com.example.moviesrating.domain.usecase.GetMovieCastByIdUseCase
import com.example.moviesrating.domain.usecase.GetMovieCastByIdUseCaseImpl
import com.example.moviesrating.domain.usecase.GetMovieDetailsByImdbIdUseCase
import com.example.moviesrating.domain.usecase.GetMovieDetailsByImdbIdUseCaseImpl
import com.example.moviesrating.domain.usecase.GetMovieListByTitleUseCase
import com.example.moviesrating.domain.usecase.GetMovieListByTitleUseCaseImpl
import com.example.moviesrating.domain.usecase.GetPopularFilmsDataUseCase
import com.example.moviesrating.domain.usecase.GetPopularFilmsDataUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun getPopularFilmsDataUseCase(movieApiRepository: MovieApiRepository): GetPopularFilmsDataUseCase =
        GetPopularFilmsDataUseCaseImpl(movieApiRepository)

    @Provides
    fun getMovieCastByIdUseCase(castApiRepository: CastApiRepository): GetMovieCastByIdUseCase =
        GetMovieCastByIdUseCaseImpl(castApiRepository)

    @Provides
    fun getActorDetailsByIdUseCase(actorDetailsApiRepository: ActorDetailsApiRepository): GetActorDetailsByIdUseCase =
        GetActorDetailsByIdUseCaseImpl(actorDetailsApiRepository)

    @Provides
    fun getMovieDetailsByImdbIdUseCase(movieApiRepository: MovieApiRepository): GetMovieDetailsByImdbIdUseCase =
        GetMovieDetailsByImdbIdUseCaseImpl(movieApiRepository)

    @Provides
    fun getMovieListByTitleUseCase(movieApiRepository: MovieApiRepository): GetMovieListByTitleUseCase =
        GetMovieListByTitleUseCaseImpl(movieApiRepository)
}
