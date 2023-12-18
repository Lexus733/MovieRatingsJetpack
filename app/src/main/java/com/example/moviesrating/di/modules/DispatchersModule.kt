package com.example.moviesrating.di.modules

import android.os.Handler
import android.os.Looper
import com.example.moviesrating.di.DefaultDispatcher
import com.example.moviesrating.di.IoDispatcher
import com.example.moviesrating.di.MainDispatcher
import com.example.moviesrating.di.MainHandler
import com.example.moviesrating.di.UnconfinedDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @UnconfinedDispatcher
    @Provides
    fun providesUnconfinedDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined

    @MainHandler
    @Provides
    fun provideMainHandler(): Handler = Handler(Looper.getMainLooper())
}
