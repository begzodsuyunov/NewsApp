package com.example.newsapp.domain.usecase

import com.example.newsapp.data.local.room.NewsEntity

interface BookmarkArticleUseCase {
    suspend fun bookmarkArticle(news: NewsEntity)

}