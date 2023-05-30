package com.gostev.news.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YoTopAppBar(title:String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        },
    )
}