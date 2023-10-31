package com.example.newsapp.di

import com.example.newsapp.domain.usecase.BookmarkArticleUseCase
import com.example.newsapp.domain.usecase.BookmarksUseCase
import com.example.newsapp.domain.usecase.CheckUseCase
import com.example.newsapp.domain.usecase.LatestNewsUseCase
import com.example.newsapp.domain.usecase.SearchUseCase
import com.example.newsapp.domain.usecase.TopHeadlinesUseCase
import com.example.newsapp.domain.usecase.UnBookmarkArticleUseCase
import com.example.newsapp.domain.usecase.impl.BookmarkArticleUseCaseImpl
import com.example.newsapp.domain.usecase.impl.BookmarksUseCaseImpl
import com.example.newsapp.domain.usecase.impl.CheckUseCaseImpl
import com.example.newsapp.domain.usecase.impl.LatestNewsUseCaseImpl
import com.example.newsapp.domain.usecase.impl.SearchUseCaseImpl
import com.example.newsapp.domain.usecase.impl.TopHeadlinesUseCaseImpl
import com.example.newsapp.domain.usecase.impl.UnBookmarkArticleUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindBookmarkArticleUseCase(impl: BookmarkArticleUseCaseImpl): BookmarkArticleUseCase

    @Binds
    fun bindBookmarkUseCase(impl: BookmarksUseCaseImpl): BookmarksUseCase

    @Binds
    fun bindLatestNewsUseCase(impl: LatestNewsUseCaseImpl): LatestNewsUseCase

//    @Binds
//    fun bindSearchBookmarkUseCase(impl: SearchBookmarksUseCaseImpl): SearchBookmarksUseCase

    @Binds
    fun bindSearchUseCase(impl: SearchUseCaseImpl): SearchUseCase

    @Binds
    fun bindTopHeadlinesUseCase(impl: TopHeadlinesUseCaseImpl): TopHeadlinesUseCase

    @Binds
    fun bindUnBookmarkArticleUseCase(impl: UnBookmarkArticleUseCaseImpl): UnBookmarkArticleUseCase


    @Binds
    fun checkUseCase(impl: CheckUseCaseImpl): CheckUseCase
}