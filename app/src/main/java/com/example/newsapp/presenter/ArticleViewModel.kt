package com.example.newsapp.presenter

import com.example.newsapp.data.local.room.NewsEntity

interface ArticleViewModel {
    suspend fun check(title: String):Boolean

    fun bookmarkArticle(news: NewsEntity)

    fun unBookmarkArticle(news: NewsEntity)
}