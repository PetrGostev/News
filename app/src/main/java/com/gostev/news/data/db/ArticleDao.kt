package com.gostev.news.data.db

import androidx.room.*
import com.gostev.news.data.api.response.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getArticles(): List<Article>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}