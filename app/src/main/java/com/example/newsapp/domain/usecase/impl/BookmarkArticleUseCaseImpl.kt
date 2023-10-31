package com.example.newsapp.domain.usecase.impl

import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.BookmarkArticleUseCase
import javax.inject.Inject

class BookmarkArticleUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : BookmarkArticleUseCase {
    override suspend fun bookmarkArticle(news: NewsEntity) = repository.bookmarkArticle(news)
}