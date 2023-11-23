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
import com.example.moviesrating.presentation.ui.theme.SearchSubTitleTextColor

@Preview(showBackground = true, backgroundColor = (0X000))
@Composable
fun SearchSubTitle(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.search_sub_title_test_text)
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 12.sp,
        lineHeight = 19.2.sp,
        fontFamily = FontFamily(Font(R.font.montserrat_thin)),
        fontWeight = FontWeight(500),
        color = SearchSubTitleTextColor,
        textAlign = TextAlign.Center,
        letterSpacing = 0.12.sp
    )
}