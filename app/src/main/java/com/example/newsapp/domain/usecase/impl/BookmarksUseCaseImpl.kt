package com.example.newsapp.domain.usecase.impl

import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.BookmarksUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookmarksUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : BookmarksUseCase {
    override suspend fun bookmarks(): Flow<List<NewsEntity>> = repository.bookmarks()

}