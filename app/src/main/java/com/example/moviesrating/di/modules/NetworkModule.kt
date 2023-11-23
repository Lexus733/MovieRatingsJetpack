package com.example.moviesrating.di.modules

import com.example.moviesrating.BuildConfig
import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.data.remote.repository.MovieApiRepository
import com.example.moviesrating.data.remote.repository.MovieApiRepositoryImpl
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.utils.okhttp.LoggingInterceptor
import com.example.moviesrating.utils.retrofit.NetworkResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor { chain ->
            chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                .addHeader("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .build().let(chain::proceed)
        }
        .addInterceptor(LoggingInterceptor())
        .build()

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
        .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(MovieApi::class.java)

}