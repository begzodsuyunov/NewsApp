package com.example.newsapp.domain.usecase

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

interface LatestNewsUseCase {
    suspend fun latestNews(): Flow<PagingData<Article>>

}