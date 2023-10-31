package com.example.newsapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.data.remote.model.Article
import retrofit2.HttpException
import java.io.IOException

class TopDataSource(private val newsApi: NewsApi) :
    PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = newsApi.topNews(country = null, category = "general")

            if (response.isSuccessful) {
                Log.d("@@@", "data source success")
                Log.d("@@@", "${response.body()!!.articles}")
            }
            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position > 1) position - 1 else null,
                nextKey = if (position < response.body()!!.totalResults / 15) position + 1 else null
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

}