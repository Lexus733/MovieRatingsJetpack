package com.example.moviesrating.di.modules

import com.example.moviesrating.BuildConfig
import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.data.remote.repository.MovieApiRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

object RetrofitApiModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor { chain ->
            chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                .addHeader("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .build().let(chain::proceed)
        }.build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideMovieApi(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
    ): MovieApi = Retrofit.Builder()
        .baseUrl("https://moviesminidatabase.p.rapidapi.com")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
        .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        api: MovieApi,
    ): MovieApiRepository = MovieApiRepositoryImpl(api = api)
}