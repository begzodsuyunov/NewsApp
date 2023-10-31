package com.example.newsapp.presenter.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.domain.usecase.SearchUseCase
import com.example.newsapp.presenter.SearchViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModelImpl @Inject constructor(
    private val searchUseCase: SearchUseCase
) : SearchViewModel, ViewModel() {


    override val searchListFlow =
        MutableSharedFlow<PagingData<Article>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun search(search: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.search(search).collectLatest {
                Log.d("SSS", "$it vmodel")
                searchListFlow.emit(it)
            }
        }
    }
}