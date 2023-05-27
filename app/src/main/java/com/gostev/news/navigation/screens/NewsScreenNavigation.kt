package com.gostev.news.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.news.view.NewsScreen
import com.gostev.news.utils.NEWS_SCREEN


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.newsScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit
) {
    composable(NEWS_SCREEN) {
        NewsScreen(
            currentRoute = currentRoute,
            onClickBottomNavigation = onClickBottomNavigation
        )
    }
}

fun NavHostController.navigateToNews() {
    navigate(NEWS_SCREEN)
}