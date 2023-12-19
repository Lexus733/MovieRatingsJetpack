package com.example.moviesrating

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

const val MEMORY_CACHE_MAX_SIZE = 0.1
const val DISK_CACHE_MAX_SIZE = 0.03

// Что добавить
// room (кэшировать данные и запросы в сеть чтобы не было лишних запросов)
// retrofit (добавить что-то типо проверки на DDOS)
// firebase (аналитика, краши, пуши)
// библиотеки для нахождения уязвимостей типо checkmarx
// тесты и пайпу для покрытия тестами
// проверка на запросы когда нету инета
@HiltAndroidApp
class BaseApplication : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this)
            .newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(MEMORY_CACHE_MAX_SIZE)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(DISK_CACHE_MAX_SIZE)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }
}
