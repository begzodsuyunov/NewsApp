package com.example.newsapp.domain.usecase

import com.example.newsapp.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

interface TopHeadlinesUseCase {

    suspend fun topHeadlines(category: String): Flow<List<Article>>

}