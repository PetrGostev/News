package com.gostev.news.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.search.view.SearchScreen
import com.gostev.news.utils.SEARCH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    onClickItem: (article: Article) -> Unit) {
    composable(SEARCH_SCREEN) {
        SearchScreen( currentRoute = currentRoute,
            onClickBottomNavigation = onClickBottomNavigation,
            onClickItem = onClickItem)
    }
}

fun NavHostController.navigateToSearch() {
    navigate(SEARCH_SCREEN)
}