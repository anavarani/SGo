package com.varani.data

import com.varani.data.network.model.MovieDto
import com.varani.data.network.retrofit.RetrofitSGoNetworkApi

/**
 * Created by Ana Varani on 26/04/2023.
 */
interface MovieRepository {
    suspend fun getAllMovies(): List<Movie>
}

class CachedMovieRepository(
    private val api: RetrofitSGoNetworkApi
) : MovieRepository {
    override suspend fun getAllMovies(): List<Movie> {
        return api.getMovies().map {
            it.toExternalModel()
        }
    }
}

fun MovieDto.toExternalModel() = Movie(
    genre,
    title
)

data class Movie(
    var genre: String? = null,
    var title: String? = null,
)