package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NewsDatabase::class.java, "NewsDB").build()

    @Provides
    @Singleton
    fun providesDao(db: NewsDatabase) = db.newsDao()

}