package com.varani.data

import com.varani.data.network.model.MovieDto
import com.varani.data.network.retrofit.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Ana Varani on 26/04/2023.
 */
interface MovieRepository {
    fun getAllMovies(): Flow<List<Movie>>
}

class CachedMovieRepository(
    private val api: MovieService
) : MovieRepository {
    override fun getAllMovies(): Flow<List<Movie>> {
        return api.getMovies().mapToMovies()
    }
}

private fun Flow<List<MovieDto>>.mapToMovies(): Flow<List<Movie>> {
    return this.map { listDto ->
        listDto.map {
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