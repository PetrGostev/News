package com.gostev.news.data.di

import android.content.Context
import androidx.room.Room
import com.gostev.news.data.api.NewsApi
import com.gostev.news.data.db.ArticleDao
import com.gostev.news.data.db.ArticleDb
import com.gostev.news.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun oktHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): NewsApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oktHttpClient())
            .build()
            .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideArticleDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ArticleDb::class.java,
            "article_db"
        ).build()

    @Provides
    fun provideArticleDao(appDDataBase: ArticleDb): ArticleDao {
        return appDDataBase.getArticleDao()
    }
}