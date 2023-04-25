package com.varani.data.network

import com.varani.data.network.model.MovieDto

/**
 * Created by Ana Varani on 25/04/2023.
 */
interface SGoDataSource {
    suspend fun getMovies(): List<MovieDto>
}