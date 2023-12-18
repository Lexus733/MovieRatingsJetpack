package com.example.moviesrating.utils

import androidx.annotation.StringRes
import com.example.moviesrating.R

object RouteConst {

    // Screens
    const val HOME = "home"
    const val SEARCH = "search"
    const val SAVE_WATCH = "save_watch"
    const val MOVIE_DETAIL = "movie_detail"
}

enum class Route(@StringRes val title: Int) {
    HOME_SCREEN(R.string.screen_home_title),
    SEARCH_SCREEN(R.string.screen_search_title),
    SAVE_WATCH_SCREEN(R.string.screen_save_watch_title),
    MOVIE_DETAIL(R.string.screen_movie_detail),
}
