package com.example.moviesrating.presentation.screens.general

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.compose.rememberNavController
import com.example.moviesrating.presentation.nav.NavGraph
import com.example.moviesrating.presentation.nav.bottomnav.BottomNavigationCustom
import com.example.moviesrating.presentation.ui.components.TopAppBarCustom
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.presentation.ui.theme.MoviesRatingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GeneralActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val focusManager = LocalFocusManager.current
            val navController = rememberNavController()
            val interactionSource = remember { MutableInteractionSource() }

            MoviesRatingTheme {
                Scaffold(
                    containerColor = BackgroundColor,
                    topBar = {
                        TopAppBarCustom(
                            navController
                        )
                    },
                    bottomBar = {
                        BottomNavigationCustom(
                            navController = navController
                        )
                    },
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {
                            focusManager.clearFocus()
                        }
                    )
                ) { innerPadding ->
                    NavGraph(
                        navHostController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}
