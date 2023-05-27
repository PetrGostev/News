package com.gostev.news.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.favorite.view.FavoriteScreen
import com.gostev.news.utils.FAVORITE_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favoriteScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    onClickItem: (article: Article) -> Unit) {

    composable(FAVORITE_SCREEN) {
        FavoriteScreen(
            currentRoute = currentRoute,
            onClickBottomNavigation = onClickBottomNavigation,
            onClickItem = onClickItem
        )
    }
}

fun NavHostController.navigateToFavorite() {
    navigate(FAVORITE_SCREEN)
}