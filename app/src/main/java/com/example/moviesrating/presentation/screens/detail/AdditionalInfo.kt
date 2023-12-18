package com.example.moviesrating.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviesrating.R
import com.example.moviesrating.presentation.ui.theme.BackgroundColor

@Composable
fun AdditionalInfoTabRow(state: MovieDetailViewState.Display) {
    val tabs = listOf("About Movie", "Cast")
    var tabIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = BackgroundColor,
                divider = {}
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                modifier = Modifier,
                                text = title,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.W500
                            )
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            when (tabIndex) {
                0 -> AdditionalInfoTabAbout(state)
                1 -> AdditionalInfoTabCast(state)
            }
        }
    }
}

@Composable
private fun AdditionalInfoTabAbout(
    state: MovieDetailViewState.Display
) {
    Text(
        text = state.movie.description,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.W400,
            color = Color.White,
        ),
        modifier = Modifier
            .padding(top = 24.dp, start = 8.dp, end = 8.dp)
    )
}

@Composable
private fun AdditionalInfoTabCast(
    state: MovieDetailViewState.Display
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.height(500.dp),
        content = {
            items(state.movie.castList!!) {
                AdditionalInfoTabCastItem(
                    imageUrl = it.image_url ?: "",
                    name = it.name
                )
            }
        }
    )
}

@Composable
private fun AdditionalInfoTabCastItem(
    imageUrl: String,
    name: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier.size(100.dp),
            shape = CircleShape
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = name,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins)),
            fontWeight = FontWeight.W500,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
