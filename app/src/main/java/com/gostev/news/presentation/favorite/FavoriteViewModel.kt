package com.gostev.news.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gostev.news.data.api.response.Article
import com.gostev.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val favoriteNewsState = MutableStateFlow(FavoriteNewsState())

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        favoriteNewsState.update { value ->
            value.copy(
                loading = false,
                error = throwable.localizedMessage as String
            )
        }
    }

    init {
        favoriteNewsState.update { value ->
            value.copy(
                loading = true
            )
        }
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch(exceptionHandler) {
            favoriteNewsState.update { value ->
                value.copy(
                    loading = false,
                    news = repository.getFavoriteArticles() ?: emptyList()
                )
            }
        }
    }

    fun deleteArticleFavorite(article: Article) {
        viewModelScope.launch(exceptionHandler) {
            repository.deleteFromFavorite(article)
            getFavorites()
        }
    }
}