package com.example.moviesrating.di.modules

import android.content.Context
import android.os.Handler
import com.example.moviesrating.BuildConfig
import com.example.moviesrating.data.remote.api.MovieApi
import com.example.moviesrating.di.MainHandler
import com.example.moviesrating.utils.okhttp.LoggingInterceptor
import com.example.moviesrating.utils.retrofit.NetworkResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(
        loggingInterceptor: LoggingInterceptor,
        @ApplicationContext context: Context
    ): OkHttpClient = OkHttpClient()
        .newBuilder()
        .cache(
            Cache(
                directory = File(context.applicationContext.cacheDir, "http_cache"),
                maxSize = 50L * 1024L * 1024L // 50 MiB
            )
        )
        .addInterceptor { chain ->
            chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", BuildConfig.API_KEY)
                .addHeader("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .build().let(chain::proceed)
        }
        .addInterceptor(loggingInterceptor)
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

    @Singleton
    @Provides
    fun provideLoggingInterceptor(
        @ApplicationContext context: Context,
        @MainHandler handler: Handler
    ): LoggingInterceptor =
        LoggingInterceptor(context, handler)
}
