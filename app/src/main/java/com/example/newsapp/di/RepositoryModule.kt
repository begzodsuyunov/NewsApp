package com.example.newsapp.di

import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun newsRepository(impl: NewsRepositoryImpl): NewsRepository
}