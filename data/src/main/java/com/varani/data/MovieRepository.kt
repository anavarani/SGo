package com.varani.data

import com.varani.data.network.model.MovieDto
import com.varani.data.network.retrofit.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Ana Varani on 26/04/2023.
 */
interface MovieRepository {
    fun getAllMovies(): Flow<List<Movie>>
}

class CachedMovieRepository @Inject constructor(
    private val api: MovieService
) : MovieRepository {
    override fun getAllMovies(): Flow<List<Movie>> = flow {
        api.getMovies()
            .map { it.toExternalModel() }
            .apply {
                emit(this)
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
) {
    fun doesMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$genre",
            "$title",
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}