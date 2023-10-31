package com.example.newsapp.domain.usecase

import com.example.newsapp.data.local.room.NewsEntity

interface UnBookmarkArticleUseCase {
    suspend fun unBookmarkArticle(news: NewsEntity)

}