package com.gostev.news.data.api.response

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val code: String?,
    val message: String?,
    val totalResults: Int,
)
