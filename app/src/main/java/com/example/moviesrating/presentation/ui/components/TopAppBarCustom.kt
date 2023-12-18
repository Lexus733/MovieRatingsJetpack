package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviesrating.R
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.presentation.ui.theme.TopAppBarIconColor
import com.example.moviesrating.presentation.ui.theme.TopAppBarTitleColor
import com.example.moviesrating.utils.Route
import com.example.moviesrating.utils.RouteConst
import java.lang.RuntimeException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route

    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = BackgroundColor,
            titleContentColor = TopAppBarIconColor,
        ),
        title = {
            Text(
                text = stringResource(id = getTitle(route = currentRoute)),
                color = TopAppBarTitleColor,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                fontWeight = FontWeight.W600,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_left_24),
                    contentDescription = null,
                    tint = TopAppBarIconColor
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /* do something */ },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(
                        id = when (currentRoute) {
                            RouteConst.MOVIE_DETAIL -> R.drawable.ic_bookmark_off_24
                            else -> R.drawable.ic_info_circle_24
                        }
                    ),
                    contentDescription = null,
                    tint = TopAppBarIconColor
                )
            }
        }
    )
}

private fun getTitle(route: String?): Int {
    return when (route) {
        RouteConst.HOME -> Route.HOME_SCREEN.title
        RouteConst.SEARCH -> Route.SEARCH_SCREEN.title
        RouteConst.SAVE_WATCH -> Route.SAVE_WATCH_SCREEN.title
        RouteConst.MOVIE_DETAIL -> Route.MOVIE_DETAIL.title
        else -> R.string.screen_undefined
    }
}
