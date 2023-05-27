package com.gostev.news.presentation.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gostev.news.data.api.response.Article
import com.gostev.news.repository.NewsRepository
import retrofit2.HttpException
import java.io.IOException

class NewsPagingSource(
    private val newsRepository: NewsRepository,
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val nextPageNumber = params.key ?: STARTING_PAGE

        return try {
            val mewsItems =  newsRepository.getPageNews(nextPageNumber).articles

            LoadResult.Page(
                data = mewsItems,
                prevKey = if (nextPageNumber == STARTING_PAGE) null else nextPageNumber - 1,
                nextKey = if (mewsItems.isEmpty()) null else nextPageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object{
        const val STARTING_PAGE = 0
    }
}