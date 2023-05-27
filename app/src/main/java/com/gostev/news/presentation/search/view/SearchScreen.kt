package com.gostev.news.presentation.search.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.gostev.news.R
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.search.SearchViewModel
import com.gostev.news.views.*


@Composable
fun SearchScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    onClickItem: (article: Article) -> Unit,
) {
    val viewModel: SearchViewModel = hiltViewModel()
    val queryTextState by viewModel.queryTextState.collectAsStateWithLifecycle()
    val searchNewsState by viewModel.searchNewsState.collectAsStateWithLifecycle()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = searchNewsState.loading)

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    SearchView(queryTextState.submittedText,
                        onValueChange = { viewModel.onQueryTextChange(it) },
                        onClearClick = { viewModel.onQueryTextChange("") })
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
            if (searchNewsState.error) {
                MyDialog(
                    onDismiss = {},
                    onPositiveClick = {},
                    title = stringResource(id = R.string.error_title),
                    text = searchNewsState.message?:"",
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
                SearchArticleListView(searchNewsState.news,
                    {onClickItem.invoke(it)},
                    { article ->
                        viewModel.checkArticleFavorite(article)
                    })
            }
        })
}