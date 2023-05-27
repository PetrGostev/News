package com.gostev.news.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gostev.news.data.api.NewsApi
import com.gostev.news.data.api.response.Article
import com.gostev.news.data.api.response.NewsResponse
import com.gostev.news.data.db.ArticleDao
import com.gostev.news.presentation.news.NewsPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsService: NewsApi,
    private val articleDao: ArticleDao
) {

    fun getNews(): Flow<PagingData<Article>> = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { NewsPagingSource(this) }
    ).flow

    suspend fun getPageNews(page: Int): NewsResponse =
        newsService.getHeadLines(page = page)

    suspend fun searchNews(queryString: String): NewsResponse =
        newsService.getEverything(queryString,page = 1)

    suspend fun getFavoriteArticles() =  withContext(Dispatchers.IO) {articleDao.getArticles()}

    suspend fun addToFavorite(article: Article) = articleDao.insert(article = article)

    suspend fun deleteFromFavorite(article: Article) = articleDao.delete(article = article)

    companion object {
        const val PAGE_SIZE = 10
    }
}