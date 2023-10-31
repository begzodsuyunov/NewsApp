package com.example.newsapp.domain.usecase

import com.example.newsapp.data.local.room.NewsEntity
import kotlinx.coroutines.flow.Flow

interface CheckUseCase {

    suspend fun check(title: String): NewsEntity?

}