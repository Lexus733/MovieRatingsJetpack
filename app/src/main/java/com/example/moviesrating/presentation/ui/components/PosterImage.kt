package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesrating.data.remote.model.moviebyimdbid.DataEntityMovieByImdbId
import com.example.moviesrating.presentation.ui.theme.BackgroundColor

@Composable
fun PosterImage(dataEntityMovieByImdbId: DataEntityMovieByImdbId) {
    Card(
        modifier = Modifier
            .size(width = 140.dp, height = 210.dp)
            .padding(start = 12.dp, end = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundColor
        )
    ) {
        AsyncImage(
            model = dataEntityMovieByImdbId.results.banner,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}