package com.gostev.news.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gostev.news.data.api.response.Article
import com.gostev.news.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel  @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val news = repository.getNews().cachedIn(viewModelScope)
}