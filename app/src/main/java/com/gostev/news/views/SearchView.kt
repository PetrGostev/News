package com.gostev.news.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun SearchView(
    submittedText: String,
    onValueChange: (value: String) -> Unit,
    onClearClick: () -> Unit
) {
    TextField(
        value = submittedText,
        onValueChange = { onValueChange.invoke(it) },
        placeholder = { Text(text = "search") },
        leadingIcon ={ Icon(imageVector = Icons.Rounded.Search, contentDescription = "")},
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black,
        ),
        singleLine = true,
        trailingIcon = {
            AnimatedVisibility(
                visible = submittedText.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton(onClick = { onClearClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = ""
                    )
                }

            }
        },
        shape = RoundedCornerShape(size = 12.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White,
            placeholderColor = Color.Gray)
    )
}