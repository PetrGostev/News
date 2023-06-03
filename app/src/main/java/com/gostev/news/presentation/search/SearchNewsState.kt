package com.gostev.news.presentation.search

import com.gostev.news.data.api.response.Article

data class SearchNewsState(
    var loading: Boolean = false,
    var error: Boolean = false,
    var success: Boolean = false,
    val message: String? = null,
    val news: List<Article> = emptyList()
)
