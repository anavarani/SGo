package com.varani.sgo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.data.CachedMovieRepository
import com.varani.data.Movie
import com.varani.data.Result
import com.varani.data.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created by Ana Varani on 26/04/2023.
 */
@HiltViewModel
class MainViewModel(
    private val movieRepository: CachedMovieRepository
) : ViewModel() {

    val uiState: StateFlow<UiState> =
        movieRepository
            .getAllMovies()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Loading -> UiState.Loading
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState.Loading,
            )
}

sealed class UiState {
    object Loading : UiState()

    data class Success(
        val feed: List<Movie>,
    ) : UiState()

    data class Error(
        val throwable: Throwable? = null
    ) : UiState()
}