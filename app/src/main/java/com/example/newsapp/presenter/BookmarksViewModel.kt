package com.example.newsapp.presenter

import com.example.newsapp.data.local.room.NewsEntity
import kotlinx.coroutines.flow.Flow

interface BookmarksViewModel {
    val bookmarksList: Flow<List<NewsEntity>>
    val searchBookmarksList: Flow<List<NewsEntity>>
}