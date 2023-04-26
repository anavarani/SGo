package com.varani.data.network.retrofit

import com.varani.data.network.model.MovieDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

/**
 * Created by Ana Varani on 25/04/2023.
 */
interface MovieService {
    @GET(value = "759fdfa82d6f33522e11")
    fun getMovies(): Flow<List<MovieDto>>
}

const val SGoBaseUrl = "https://api.npoint.io/"