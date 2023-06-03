package com.gostev.news.presentation.search.data

import com.gostev.news.data.api.response.Article

data class SearchNewsState(
    val submittedText: String = "",
    var loading: Boolean = false,
    var error: Boolean = false,
    var success: Boolean = false,
    val message: String? = null,
    val news: List<Article> = emptyList()
)
