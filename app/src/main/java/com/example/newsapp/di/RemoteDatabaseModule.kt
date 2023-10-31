package com.example.newsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDatabaseModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun chuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context).build()


    @Provides
    @Singleton
    fun client(loggingInterceptor: HttpLoggingInterceptor, chuckerInterceptor: ChuckerInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)
            .build()

    @Provides
    fun providesNewsRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    fun newsApi(retrofit: Retrofit) = retrofit.create(NewsApi::class.java)
}