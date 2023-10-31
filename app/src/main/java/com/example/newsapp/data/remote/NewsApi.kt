package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.model.BaseNewsData
import com.example.newsapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything?")
    suspend fun everything(
        @Query("q") search: String = "musk",
        @Query("searchIn") searchIn: String = "title",
        @Query("from") from: String = "",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("language") language: String = "en",
        @Query("pageSize") pageSize: Int = 15,
        @Query("page") pageCount: Int = 6,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<BaseNewsData>


    @GET("top-headlines")
    suspend fun topNews(
        @Query("country") country: String?,
        @Query("category") category: String = "business",
        @Query("pageSize") pageSize: Int = 15,
        @Query("page") pageCount: Int = 6,
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<BaseNewsData>
}