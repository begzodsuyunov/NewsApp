package com.example.newsapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.data.local.room.NewsDao
import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.remote.EverythingDataSource
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.TopDataSource
import com.example.newsapp.data.remote.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val dao: NewsDao
) : NewsRepository {


    override fun latestNews(): Flow<PagingData<Article>> = Pager(PagingConfig(pageSize = 15)) {
        TopDataSource(newsApi)
    }.flow

    override suspend fun topHeadlines(category: String): Flow<List<Article>> = flow {
        val response = newsApi.topNews(category = category, country = null)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it.articles)
            }
        } else {
            Log.d("@@@", "response not successful")
        }
    }.flowOn(Dispatchers.IO)
        .catch { error ->
            Log.d("@@@", "${error.message}")
        }

    override fun search(search: String): Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = 15)) {
            EverythingDataSource(search, newsApi)
        }.flow

    override fun bookmarks(): Flow<List<NewsEntity>> = dao.getAll()

    override fun searchBookmarks(search: String): Flow<List<NewsEntity>> =
        dao.search(search)

    override suspend fun bookmarkArticle(news: NewsEntity) = dao.insert(news)

    override suspend fun unBookmarkArticle(news: NewsEntity) = dao.delete(news)

    override suspend fun check(title: String): NewsEntity? = dao.check(title)
}