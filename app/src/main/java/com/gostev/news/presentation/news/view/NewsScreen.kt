package com.gostev.news.presentation.news.view

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.gostev.news.presentation.news.NewsViewModel
import com.gostev.news.views.NewsBottomBar

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
            TopAppBar(
                title = {
                    Text(
                        text = "News",
                        color = Color.Black,
                        fontSize = 22.sp
                    )
                },
            )
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