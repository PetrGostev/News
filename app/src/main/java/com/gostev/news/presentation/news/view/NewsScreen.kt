package com.gostev.news.presentation.news.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.gostev.news.R
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.news.NewsViewModel
import com.gostev.news.views.MyDialog
import com.gostev.news.views.NewsBottomBar
import com.gostev.news.views.YoTopAppBar
import kotlinx.coroutines.flow.flow


@Composable
internal fun NewsScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
) {
    val viewModel: NewsViewModel = hiltViewModel()
    val news = viewModel.news.collectAsLazyPagingItems()
    NewsScreen(
        currentRoute = currentRoute,
        onClickBottomNavigation = onClickBottomNavigation,
        news = news
    )
}

@Composable
private fun NewsScreen(
    currentRoute: String?,
    onClickBottomNavigation: (route: String) -> Unit,
    news: LazyPagingItems<Article>
) {
    var error by rememberSaveable {
        mutableStateOf("")
    }
    var refreshing by rememberSaveable {
        mutableStateOf(true)
    }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = refreshing)
    var isShowDialog by rememberSaveable {
        mutableStateOf(false)
    }

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
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = {
                    news.refresh()
                },
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    items(
                        count = news.itemCount,
                        key = news.itemKey(),
                        contentType = news.itemContentType(),

                        ) { index ->
                        val item = news[index]
                        item?.let { ArticleView(article = it, isVisibleImage = false, {}, {}) }
                    }
                }
            }
            if (news.loadState.refresh is LoadState.NotLoading) {
                refreshing = false
            }
            if (news.loadState.refresh is LoadState.Error) {
                error = (news.loadState.refresh as LoadState.Error).error.message.toString()
                refreshing = false
                isShowDialog = true
            }

            if (isShowDialog) {
                MyDialog(
                    onDismiss = { isShowDialog = false },
                    onPositiveClick = { isShowDialog = false },
                    title = stringResource(id = R.string.error_title),
                    text = error,
                    textButton = stringResource(id = R.string.ok)
                )
            }
        })
}

@Preview(showSystemUi = true)
@Composable
fun PreviewNewsScreen() {
    val newsPagingData = flow<PagingData<Article>> {
        emit(
            PagingData.from(
                listOf(
                    Article(
                        0, null, "Hello", "start",
                        "0000000000", "Title", null, null
                    ), Article(
                        0, null, "Hello", "start",
                        "0000000000", "Title", null, null
                    )
                )
            )
        )
    }
    val news = newsPagingData.collectAsLazyPagingItems()
    NewsScreen(
        currentRoute = "",
        onClickBottomNavigation = {},
        news
    )
}
