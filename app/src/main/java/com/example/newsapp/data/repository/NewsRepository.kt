package com.example.newsapp.data.repository

import androidx.paging.PagingData
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NewsRepository {

    fun latestNews(): Flow<PagingData<Article>>

    suspend fun topHeadlines(category: String): Flow<List<Article>>

    fun search(search: String): Flow<PagingData<Article>>

    fun bookmarks(): Flow<List<NewsEntity>>

    fun searchBookmarks(search: String): Flow<List<NewsEntity>>

    suspend fun bookmarkArticle(news: NewsEntity)

    suspend fun unBookmarkArticle(news: NewsEntity)

    suspend fun check(title: String): NewsEntity?
}