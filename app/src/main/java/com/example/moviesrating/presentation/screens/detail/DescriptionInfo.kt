package com.example.moviesrating.presentation.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
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
import com.example.moviesrating.presentation.ui.theme.MovieDetailDescriptionColor
import com.example.moviesrating.presentation.ui.theme.MovieDetailDividerColor

@Composable
fun DescriptionInfo(
    state: MovieDetailViewState.Display,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(top = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            DescriptionInfoItem(
                id = R.drawable.ic_calendar_blank,
                text = state.movie.year.toString()
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                thickness = 1.dp,
                color = MovieDetailDividerColor
            )
            DescriptionInfoItem(
                id = R.drawable.ic_clock,
                text = state.movie.movie_length
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp),
                thickness = 1.dp,
                color = MovieDetailDividerColor
            )
            DescriptionInfoItem(
                id = R.drawable.ic_ticket,
                text = state.movie.gen.first().genre
            )
        }
    }
}

@Composable
private fun DescriptionInfoItem(id: Int, text: String) {
    Row {
        Icon(
            painter = painterResource(id = id),
            contentDescription = null,
            tint = MovieDetailDescriptionColor,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = text,
            color = MovieDetailDescriptionColor,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            fontWeight = FontWeight.W500,
            letterSpacing = 0.12.sp
        )
    }
}
