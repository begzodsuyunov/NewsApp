package com.example.newsapp.presenter.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.domain.usecase.LatestNewsUseCase
import com.example.newsapp.domain.usecase.TopHeadlinesUseCase
import com.example.newsapp.presenter.NewsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val latestNewsUseCase: LatestNewsUseCase,
    private val topHeadlinesUseCase: TopHeadlinesUseCase

) : NewsViewModel, ViewModel() {


    override val latestNewsList = MutableSharedFlow<PagingData<Article>>()

    override val topHeadlinesList = MutableSharedFlow<List<Article>>()

    override fun topHeadlines(category: String) {
        viewModelScope.launch {
            topHeadlinesUseCase.topHeadlines(category).collectLatest {
                topHeadlinesList.emit(it)
            }
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            latestNewsUseCase.latestNews().collectLatest {
                latestNewsList.emit(it)
            }
        }
//        viewModelScope.launch(IO) {
//            topHeadlinesUseCase.topHeadlines("business").collectLatest {
//                topHeadlinesList.emit(it)
//            }
//        }


    }
}