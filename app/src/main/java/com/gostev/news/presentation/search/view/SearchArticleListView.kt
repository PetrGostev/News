package com.gostev.news.presentation.search.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.news.view.ArticleView

@Composable
fun SearchArticleListView(
    news: List<Article>, onClickItem: (article: Article) -> Unit,
    onClickFavorite: ( article: Article) -> Unit
) {
    LazyColumn() {
        items(
            count = news.count(),
        ) { index ->
            val item = news[index]
            ArticleView(article = item, isVisibleImage = true, { article ->
                onClickItem.invoke(
                    article
                )
            }, {onClickFavorite.invoke(item)})
        }
    }
}

