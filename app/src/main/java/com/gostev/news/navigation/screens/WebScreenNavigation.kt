package com.gostev.news.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.gostev.news.presentation.web.WebScreen
import com.gostev.news.utils.WEB_SCREEN

private const val URL_KEY = "urlKey"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.webScreen(
    currentRoute: String?
) {

    composable(WEB_SCREEN) {
        val url = it.savedStateHandle.get<String>(URL_KEY).orEmpty()
        WebScreen(
            currentRoute = currentRoute,
            url
        )
    }
}

fun NavHostController.navigateToWeb(url: String) {
    navigate(WEB_SCREEN)
    currentBackStackEntry?.let {
        it.savedStateHandle[URL_KEY] = url
    }
}