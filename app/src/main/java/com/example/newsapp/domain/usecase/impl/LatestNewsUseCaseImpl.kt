package com.example.newsapp.domain.usecase.impl

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.LatestNewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LatestNewsUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : LatestNewsUseCase{
    override suspend fun latestNews(): Flow<PagingData<Article>> = repository.latestNews()
}