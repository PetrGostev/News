package com.gostev.news.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gostev.news.data.api.response.Article
import com.gostev.news.presentation.search.data.SearchNewsEvent
import com.gostev.news.presentation.search.data.SearchNewsState
import com.gostev.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SUCCESS = "ok"

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val searchNewsState = MutableStateFlow(SearchNewsState())

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        searchNewsState.update { value ->
            value.copy(
                loading = false,
                error = true,
                success = false,
                message = throwable.localizedMessage,
                news = emptyList()
            )
        }
    }

    fun send(event: SearchNewsEvent) {
        when (event) {
            is SearchNewsEvent.SearchEvent -> {onQueryTextChange(queryText = event.query)}
            is SearchNewsEvent.FavoriteEvent -> {checkArticleFavorite(article = event.article) }
        }
    }

    private fun onQueryTextChange(queryText: String) {
        viewModelScope.launch {
            searchNewsState.update { value ->
                if (queryText.isEmpty()) {
                    value.copy(
                        submittedText = queryText,
                        loading = false,
                        error = false,
                        success = false,
                        message = null,
                        news = emptyList()
                    )
                } else {
                    value.copy(
                        submittedText = queryText,
                        loading = true,
                    )
                }
            }
            if (queryText.isNotEmpty()) {
                searchNews(queryText)
            }
        }
    }

    private fun searchNews(query: String) {
        viewModelScope.launch(exceptionHandler) {
            val newsResponse = repository.searchNews(query)
            if (newsResponse.status == SUCCESS) {
                val favoriteArticles = repository.getFavoriteArticles()
                favoriteArticles?.let {
                    it.forEach { favoriteArticle ->
                        newsResponse.articles.forEach { article ->
                            if (favoriteArticle.content == article.content) {
                                article.isFavorite = favoriteArticle.isFavorite
                            }
                        }
                    }
                }

                searchNewsState.update { value ->
                    value.copy(
                        loading = false,
                        error = false,
                        success = true,
                        message = null,
                        news = newsResponse.articles
                    )
                }
            }
        }
    }

   private fun checkArticleFavorite(article: Article) {
        viewModelScope.launch {
            val newsValue = searchNewsState.value.news

            newsValue.forEach {
                if (article == it) {
                    it.isFavorite = true
                    article.isFavorite = true
                }
            }
            repository.addToFavorite(article)
            searchNewsState.update { value ->
                value.copy(news = newsValue)
            }
        }
    }
}