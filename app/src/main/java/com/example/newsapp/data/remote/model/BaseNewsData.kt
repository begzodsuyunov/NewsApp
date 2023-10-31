package com.example.newsapp.data.remote.model

data class BaseNewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
