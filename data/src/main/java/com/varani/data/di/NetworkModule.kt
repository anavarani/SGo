package com.varani.data.di

import com.varani.data.network.SGoDataSource
import com.varani.data.network.retrofit.SGoBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ana Varani on 25/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SGoBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideSGoApi(
        retrofit: Retrofit
    ): SGoDataSource {
        return retrofit.create(SGoDataSource::class.java)
    }
}