package com.example.newsapp.domain.usecase.impl

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.SearchUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : SearchUseCase{

    override suspend fun search(search: String): Flow<PagingData<Article>> =repository.search(search)

}