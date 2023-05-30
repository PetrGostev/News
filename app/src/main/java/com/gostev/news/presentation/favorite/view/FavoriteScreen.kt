package com.gostev.news.presentation.favorite.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.gostev.news.R
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.favorite.FavoriteViewModel
import com.gostev.news.views.MyDialog
import com.gostev.news.views.NewsBottomBar
import com.gostev.news.views.YoTopAppBar

@Composable
fun FavoriteScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    onClickItem: (article: Article) -> Unit,
) {
    val viewModel: FavoriteViewModel = hiltViewModel()
    val favoriteNewsState by viewModel.favoriteNewsState.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = favoriteNewsState.loading)

    Scaffold(
        modifier = Modifier,
        topBar = {
            YoTopAppBar("Favorite")
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
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                if (favoriteNewsState.error.isNotEmpty()) {
                    MyDialog(
                        onDismiss = {},
                        onPositiveClick = {},
                        title = stringResource(id = R.string.error_title),
                        text = favoriteNewsState.error,
                        textButton = stringResource(id = R.string.ok)
                    )
                    return@SwipeRefresh
                }

                if (favoriteNewsState.news.isEmpty()) {
                    Text(text = stringResource(id = R.string.not_favorite),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(22.dp))
                    return@SwipeRefresh
                }

                FavoriteArticleListView(favoriteNewsState.news,
                    {onClickItem.invoke(it)},
                    { article ->
                        viewModel.deleteArticleFavorite(article)
                    })
            }
        })
}