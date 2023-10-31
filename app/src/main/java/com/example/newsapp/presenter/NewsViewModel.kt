package com.example.newsapp.presenter

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsViewModel {

    val latestNewsList: Flow<PagingData<Article>>
    val topHeadlinesList: Flow<List<Article>>

    //    fun latestNews()

    fun topHeadlines(category: String)
}