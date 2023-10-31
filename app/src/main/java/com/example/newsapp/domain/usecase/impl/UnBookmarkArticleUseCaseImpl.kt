package com.example.newsapp.domain.usecase.impl

import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.UnBookmarkArticleUseCase
import javax.inject.Inject

class UnBookmarkArticleUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : UnBookmarkArticleUseCase {
    override suspend fun unBookmarkArticle(news: NewsEntity) =repository.unBookmarkArticle(news)

}