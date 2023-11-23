package com.example.moviesrating.presentation.screens.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesrating.R
import com.example.moviesrating.presentation.screens.general.GeneralActivity
import com.example.moviesrating.presentation.ui.theme.BackgroundColor
import com.example.moviesrating.presentation.ui.theme.MoviesRatingTheme
import dagger.hilt.android.AndroidEntryPoint

const val ANIMATION_ALPHA_INITIAL = 0f
const val ANIMATION_ALPHA_TARGET = 1f
const val ANIMATION_DURATION = 2000

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesRatingTheme {
                SplashScreen()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SplashScreen() {

        val alpha = remember {
            Animatable(ANIMATION_ALPHA_INITIAL)
        }

        LaunchedEffect(key1 = true, block = {
            alpha.animateTo(ANIMATION_ALPHA_TARGET, animationSpec = tween(ANIMATION_DURATION))
            val intent = Intent(this@SplashActivity, GeneralActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_popcorn),
                contentDescription = null,
                modifier = Modifier.alpha(alpha.value)
            )
        }
    }
}