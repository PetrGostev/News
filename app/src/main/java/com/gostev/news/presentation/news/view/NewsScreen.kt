package com.gostev.news.presentation.news.view

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.gostev.news.R
import com.gostev.news.presentation.news.NewsViewModel
import com.gostev.news.views.NewsBottomBar
import com.gostev.news.views.YoTopAppBar

@Composable
fun NewsScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
) {
    val viewModel: NewsViewModel = hiltViewModel()
    val news = viewModel.news.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier,
        topBar = {
            YoTopAppBar(stringResource(id = R.string.app_name))
        },
        bottomBar = {
            if (currentRoute != null) {
                NewsBottomBar(
                    currentRoute = currentRoute,
                    onClickBottomNavigation = onClickBottomNavigation
                )
            }
        },
        floatingActionButton = {},

        content = { padding ->
            ArticleListView(news, padding)
        })
}