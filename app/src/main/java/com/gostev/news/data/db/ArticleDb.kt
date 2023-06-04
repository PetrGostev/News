package com.gostev.news.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gostev.news.data.api.response.Article

@Database(entities = [Article::class],  version = 1, exportSchema = false)
abstract class ArticleDb : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao
}