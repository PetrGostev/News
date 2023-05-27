package com.gostev.news.presentation.favorite.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.gostev.news.data.api.response.Article


@Composable
fun FavoriteArticleListView(
    news: List<Article>,
    onClickItem: (article: Article) -> Unit,
    onClickDeleteFavorite: (article: Article) -> Unit
) {
    LazyColumn() {
        items(
            count = news.count(),
        ) { index ->
            val item = news[index]
            FavoriteArticleView(article = item, { article ->
                onClickItem.invoke(
                    article
                )
            }, {
                onClickDeleteFavorite.invoke(item)
            })
        }
    }
}