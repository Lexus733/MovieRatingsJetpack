package com.example.moviesrating.presentation.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesrating.R
import com.example.moviesrating.presentation.ui.theme.SearchFieldBackgroundColor
import com.example.moviesrating.presentation.ui.theme.SearchFieldIconColor

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String = "test text",
    onValueChanged: (String) -> Unit = {},
    onIconClick: () -> Unit = {}
) {
    val localFocusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChanged,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = SearchFieldBackgroundColor,
            textColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            IconButton(
                onClick = onIconClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_16),
                    contentDescription = null,
                    tint = SearchFieldIconColor
                )
            }
        },
        maxLines = 1,
        modifier = modifier,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            localFocusManager.clearFocus()
        })
    )
}