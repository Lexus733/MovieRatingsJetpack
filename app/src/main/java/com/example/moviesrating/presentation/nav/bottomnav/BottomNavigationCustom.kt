package com.example.moviesrating.presentation.nav.bottomnav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.presentation.ui.theme.BottomNavigationDividerColor
import com.example.moviesrating.presentation.ui.theme.ButtonItemSelectedColor
import com.example.moviesrating.presentation.ui.theme.ButtonItemUnselectedColor

@Composable
fun BottomNavigationCustom(
    navController: NavController
) {
    val listItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.SaveWish
    )

    BottomNavigation(
        backgroundColor = BackgroundColor
    ) {
        val backStack by navController.currentBackStackEntryAsState()
        val currentRoute = backStack?.destination?.route

        Column {

            Divider(color = BottomNavigationDividerColor, thickness = 1.dp)

            Row {
                listItems.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconId),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }, label = {
                            Text(
                                text = stringResource(id = item.title),
                                fontSize = 12.sp
                            )
                        },
                        selectedContentColor = ButtonItemSelectedColor,
                        unselectedContentColor = ButtonItemUnselectedColor
                    )
                }
            }
        }
    }
}