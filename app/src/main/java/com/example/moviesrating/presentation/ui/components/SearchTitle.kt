package com.example.moviesrating.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.moviesrating.R
import com.example.moviesrating.presentation.ui.theme.SearchTitleTextColor

@Preview(showBackground = true, backgroundColor = (0X000))
@Composable
fun SearchTitle(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.search_title_test_text)
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        lineHeight = 25.6.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_medium)),
        fontWeight = FontWeight.W600,
        color = SearchTitleTextColor,
        textAlign = TextAlign.Center,
        letterSpacing = 0.12.sp
    )
}
