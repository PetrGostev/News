package com.gostev.news.presentation.web

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebScreen(currentRoute: String?, url: String) {
    var visibilityProgress by remember { mutableStateOf(false)}

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(
                        view: WebView, url: String,
                        favicon: Bitmap?
                    ) {
                        visibilityProgress = true
                    }

                    override fun onPageFinished(
                        view: WebView, url: String
                    ) {
                        visibilityProgress = false
                    }
                }
                loadUrl(url)

            }
        },
            update = {
                it.loadUrl(url)
            })
        if (visibilityProgress) {
            CircularProgressIndicator(
                color = Color(0xFF5E5C5C),
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewWebScreen() {
    WebScreen(currentRoute = "", url = "")
}