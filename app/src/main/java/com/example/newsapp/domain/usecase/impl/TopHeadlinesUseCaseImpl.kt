package com.example.newsapp.domain.usecase.impl

import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.TopHeadlinesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopHeadlinesUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : TopHeadlinesUseCase{
    override suspend fun topHeadlines(category: String): Flow<List<Article>> =
        repository.topHeadlines(category)

}