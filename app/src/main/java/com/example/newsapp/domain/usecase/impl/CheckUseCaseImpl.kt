package com.example.newsapp.domain.usecase.impl

import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.usecase.CheckUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckUseCaseImpl @Inject constructor(
    private val repository: NewsRepository
) : CheckUseCase {
    override suspend fun check(title: String): NewsEntity? =repository.check(title)
}