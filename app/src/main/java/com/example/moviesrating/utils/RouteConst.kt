package com.example.moviesrating.utils

import androidx.annotation.StringRes
import com.example.moviesrating.R

object RouteConst {

    //Screens
    const val HOME = "home"
    const val SEARCH = "search"
    const val SAVE_WATCH = "save_watch"
    const val MOVIE_DETAIL = "movie_detail"
    const val UNDEFINED = "undefined error"
}

enum class Route(@StringRes val title: Int, val route: String) {
    HOME_SCREEN(R.string.screen_home_title, RouteConst.HOME),
    SEARCH_SCREEN(R.string.screen_search_title, RouteConst.SEARCH),
    SAVE_WATCH_SCREEN(R.string.screen_save_watch_title, RouteConst.SAVE_WATCH),
    MOVIE_DETAIL(R.string.screen_movie_detail, RouteConst.MOVIE_DETAIL),
}
