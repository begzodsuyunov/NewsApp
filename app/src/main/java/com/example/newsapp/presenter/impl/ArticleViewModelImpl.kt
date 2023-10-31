package com.example.newsapp.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.domain.usecase.BookmarkArticleUseCase
import com.example.newsapp.domain.usecase.CheckUseCase
import com.example.newsapp.domain.usecase.LatestNewsUseCase
import com.example.newsapp.domain.usecase.TopHeadlinesUseCase
import com.example.newsapp.domain.usecase.UnBookmarkArticleUseCase
import com.example.newsapp.presenter.ArticleViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModelImpl @Inject constructor(
    private val bookmarkArticleUseCase: BookmarkArticleUseCase,
    private val unBookmarkArticleUseCase: UnBookmarkArticleUseCase,
    private val checkUseCase: CheckUseCase
) : ArticleViewModel, ViewModel() {


    override suspend fun check(title: String): Boolean {
        return checkUseCase.check(title) != null
    }

    override fun bookmarkArticle(news: NewsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val exBookmark = checkUseCase.check(news.article!!.title!!)
            if (exBookmark == null) {
                bookmarkArticleUseCase.bookmarkArticle(news)
            } else {
                unBookmarkArticleUseCase.unBookmarkArticle(exBookmark)
            }


        }
    }

    override fun unBookmarkArticle(news: NewsEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            unBookmarkArticleUseCase.unBookmarkArticle(news)
        }
    }
}