package com.varani.sgo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varani.data.CachedMovieRepository
import com.varani.data.Movie
import com.varani.data.common.Result
import com.varani.data.common.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * Created by Ana Varani on 26/04/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    movieRepository: CachedMovieRepository
) : ViewModel() {

    private var _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val uiState: StateFlow<UiState> =
        movieRepository
            .getAllMovies()
            .combine(searchText) { list, text ->
                if (text.isBlank()) {
                    list
                } else {
                    list.filter {
                        it.doesMatchQuery(text)
                    }
                }
            }
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

    fun onTextChange(text: String) {
        _searchText.value = text
    }
}

sealed class UiState {
    object Loading : UiState()

    data class Success(
        val movies: List<Movie>,
    ) : UiState()

    data class Error(
        val throwable: Throwable? = null
    ) : UiState()
}