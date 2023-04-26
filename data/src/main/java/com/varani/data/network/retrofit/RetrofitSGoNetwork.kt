package com.varani.data.network.retrofit

import com.varani.data.network.model.MovieDto
import retrofit2.http.GET

/**
 * Created by Ana Varani on 25/04/2023.
 */
interface MovieService {
    @GET("759fdfa82d6f33522e11")
    suspend fun getMovies(): List<MovieDto>
}

const val SGoBaseUrl = "https://api.npoint.io/"