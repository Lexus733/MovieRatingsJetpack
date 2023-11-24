package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.moviesrating.R
import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.presentation.ui.theme.BackgroundColor

@Composable
fun PosterImage(dataEntityMovieByImdbId: DataEntityMovieByImdbId) {
    Card(
        modifier = Modifier
            .size(width = 140.dp, height = 210.dp)
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundColor
        )
    ) {
        // asyncimage доработать
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            model = ImageRequest.Builder(LocalContext.current)
                .data(dataEntityMovieByImdbId.results.banner)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            placeholder = painterResource(id = R.drawable.ic_placeholder_24),
            error = painterResource(id = R.drawable.ic_error_24),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,

            )
    }
}