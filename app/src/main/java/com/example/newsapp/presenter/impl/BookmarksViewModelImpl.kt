package com.example.newsapp.presenter.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.local.room.NewsEntity
import com.example.newsapp.domain.usecase.BookmarksUseCase
import com.example.newsapp.domain.usecase.CheckUseCase
import com.example.newsapp.domain.usecase.UnBookmarkArticleUseCase
import com.example.newsapp.presenter.BookmarksViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModelImpl @Inject constructor(
    private val bookmarksUseCase: BookmarksUseCase,
    private val unBookmarkArticleUseCase: UnBookmarkArticleUseCase,
    private val checkUseCase: CheckUseCase

) : BookmarksViewModel, ViewModel() {

    override val bookmarksList = MutableSharedFlow<List<NewsEntity>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val searchBookmarksList = MutableSharedFlow<List<NewsEntity>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarksUseCase.bookmarks().collectLatest {
                bookmarksList.emit(it)
                Log.d("LOGGI", "${it}")
            }
        }
    }
}