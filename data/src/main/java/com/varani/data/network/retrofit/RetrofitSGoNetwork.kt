package com.varani.data.network.retrofit

import com.google.gson.Gson
import com.varani.data.network.SGoDataSource
import com.varani.data.network.model.MovieDto
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Ana Varani on 25/04/2023.
 */
private interface RetrofitSGoNetworkApi {
    @GET(value = "759fdfa82d6f33522e11")
    suspend fun getMovies(): List<MovieDto>
}

private const val SGoBaseUrl = "https://api.npoint.io/"

class RetrofitSGoNetwork(
    gson: Gson,
) : SGoDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(SGoBaseUrl)
        .client(
            OkHttpClient.Builder()
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(RetrofitSGoNetworkApi::class.java)

    override suspend fun getMovies(): List<MovieDto> = networkApi.getMovies()
}
