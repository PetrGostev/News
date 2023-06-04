package com.gostev.news.presentation.search.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.gostev.news.R
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.search.SearchViewModel
import com.gostev.news.presentation.search.data.SearchNewsEvent
import com.gostev.news.views.*


@Composable
internal fun SearchScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    onClickItem: (article: Article) -> Unit,
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val searchNewsState by viewModel.searchNewsState.collectAsStateWithLifecycle()

    SearchScreen(
        currentRoute = currentRoute,
        onClickBottomNavigation = onClickBottomNavigation,
        onClickItem = onClickItem,
        onQueryChange = { viewModel.send(SearchNewsEvent.SearchEvent(query = it)) },
        onClickFavorite = { viewModel.send(SearchNewsEvent.FavoriteEvent(article = it)) },
        loading = searchNewsState.loading,
        submittedText = searchNewsState.submittedText,
        error = searchNewsState.error,
        message = searchNewsState.message,
        news = searchNewsState.news,
    )
}

@Composable
private fun SearchScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    onClickItem: (article: Article) -> Unit,
    onQueryChange: (query: String) -> Unit,
    onClickFavorite: (article: Article) -> Unit,
    loading: Boolean,
    submittedText: String,
    error: Boolean,
    message: String?,
    news: List<Article>
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = loading)

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    SearchView(submittedText,
                        onValueChange = { onQueryChange(it) },
                        onClearClick = { onQueryChange("") })
                }
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
            if (error) {
                MyDialog(
                    onDismiss = {},
                    onPositiveClick = {},
                    title = stringResource(id = R.string.error_title),
                    text = message ?: "",
                    textButton = stringResource(id = R.string.ok)
                )
            }

            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                SearchArticleListView(news,
                    { onClickItem.invoke(it) },
                    { article ->
                        onClickFavorite(article)
                    })
            }
        })
}

@Preview(showSystemUi = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        currentRoute = "",
        onClickBottomNavigation = {},
        onClickItem = {},
        onQueryChange = {},
        onClickFavorite = {},
        loading = true,
        submittedText = "search",
        error = false,
        message = "",
        news = emptyList(),
    )
}
