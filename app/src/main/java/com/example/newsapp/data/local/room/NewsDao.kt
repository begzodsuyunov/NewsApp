package com.example.newsapp.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(news: NewsEntity)

    @Query("SELECT * FROM newsentity WHERE title = :title")
    suspend fun check(title: String): NewsEntity?

    @Delete
    suspend fun delete(news: NewsEntity)

    @Query("SELECT * FROM newsentity")
    fun getAll(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM newsentity WHERE title LIKE '%' || :search || '%'")
    fun search(search: String): Flow<List<NewsEntity>>

}