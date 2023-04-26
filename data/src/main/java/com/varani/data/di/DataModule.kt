package com.varani.data.di

import com.varani.data.CachedMovieRepository
import com.varani.data.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Ana Varani on 25/04/2023.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    
    @Binds
    fun bindMovieRepository(cachedMovieRepository: CachedMovieRepository): MovieRepository
}