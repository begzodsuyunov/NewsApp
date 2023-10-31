package com.example.newsapp.presenter

import androidx.paging.PagingData
import com.example.newsapp.data.remote.model.Article
import kotlinx.coroutines.flow.Flow

interface SearchViewModel {

    val searchListFlow: Flow<PagingData<Article>>

    fun search(search: String)
}