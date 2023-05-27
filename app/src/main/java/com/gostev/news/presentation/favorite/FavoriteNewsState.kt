package com.gostev.news.presentation.favorite

import com.gostev.news.data.api.response.Article

data class FavoriteNewsState(
    var loading: Boolean = false,
    var error: String = "",
    val news: List<Article> = emptyList()
)
