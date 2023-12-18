package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesrating.R
import com.example.moviesrating.presentation.ui.theme.MovieDetailRateColor

@Composable
fun Rating(
    rating: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(16.dp)
                .padding(end = 4.dp),
            painter = painterResource(id = R.drawable.ic_star_rating),
            contentDescription = null,
            tint = MovieDetailRateColor
        )
        Text(
            text = rating,
            color = MovieDetailRateColor,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            fontWeight = FontWeight.W600,
            fontSize = 12.sp,
            letterSpacing = 0.12.sp
        )
    }
}
