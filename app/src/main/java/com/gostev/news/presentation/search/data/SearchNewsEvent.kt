package com.gostev.news.presentation.search.data

import com.gostev.news.data.api.response.Article


sealed class SearchNewsEvent {
    class SearchEvent(val query: String) : SearchNewsEvent()
    class FavoriteEvent(val article: Article) : SearchNewsEvent()
}
