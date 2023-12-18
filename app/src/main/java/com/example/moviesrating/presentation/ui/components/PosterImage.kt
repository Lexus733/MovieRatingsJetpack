package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesrating.R
import com.example.moviesrating.domain.model.moviedetail.EntityMovieDetail
import com.example.moviesrating.presentation.ui.theme.BackgroundColor

@Composable
fun PosterImage(entityMovieDetail: EntityMovieDetail, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(width = 140.dp, height = 210.dp)
            .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
            .clickable {
                onClick.invoke()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundColor
        )
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            model = entityMovieDetail.image_url,
            placeholder = painterResource(id = R.drawable.ic_placeholder_24),
            error = painterResource(id = R.drawable.ic_error_24),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}
