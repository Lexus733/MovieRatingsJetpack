package com.example.moviesrating.presentation.nav.bottomnav

import androidx.annotation.StringRes
import com.example.moviesrating.R
import com.example.moviesrating.utils.RouteConst

sealed class BottomNavigationItem(
    @StringRes val title: Int,
    val iconId: Int,
    val route: String
) {
    object Home : BottomNavigationItem(
        title = R.string.bottom_item_home,
        iconId = R.drawable.ic_home_24,
        route = RouteConst.HOME
    )

    object Search : BottomNavigationItem(
        title = R.string.bottom_item_search,
        iconId = R.drawable.ic_search_24,
        route = RouteConst.SEARCH
    )

    object SaveWish : BottomNavigationItem(
        title = R.string.bottom_item_save_watch,
        iconId = R.drawable.ic_save_watch_24,
        route = RouteConst.SAVE_WATCH
    )
}
