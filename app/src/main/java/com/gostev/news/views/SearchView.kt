package com.gostev.news.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.gostev.news.R


@Composable
fun SearchView(
    submittedText: String,
    onValueChange: (value: String) -> Unit,
    onClearClick: () -> Unit
) {
    YoTextField(
        value = submittedText,
        onValueChange = { onValueChange.invoke(it) },
        modifier = Modifier
            .padding(end = 10.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 20.dp)
            .height(height = 40.dp),
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        leadingIcon ={ Icon(imageVector = Icons.Rounded.Search,
            contentDescription = "",) },
        textStyle = TextStyle(color = Color.Black),
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
                        contentDescription = "",
                    )
                }
            }
        },
        shape = RoundedCornerShape(size = 12.dp),
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White,
            placeholderColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent)
    )
}