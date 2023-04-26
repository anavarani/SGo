package com.varani.sgo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.varani.data.Movie
import com.varani.sgo.ui.theme.SGoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SGoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SGoApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SGoApp() {
    Scaffold(
        topBar = { SearchBar() }
    ) { innerPadding ->
        SearchContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TopAppBar(
        title = {
            Text(text = "Top App Bar")
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        UiState.Loading -> CircularProgressIndicator()
        is UiState.Success -> MovieListContent((uiState as UiState.Success).movies, modifier)
        is UiState.Error -> Log.d("SearchContent", "SearchContent: Error")
    }
}

@Composable
fun MovieListContent(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        modifier = modifier,
    ) {
        movies.forEach { movie ->
            item {
                MovieListItem(movie)
            }
        }
    }
}

@Composable
private fun MovieListItem(movie: Movie, modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(.6f),
        tonalElevation = 12.dp,
        shadowElevation = 12.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Text(text = "Title", style = MaterialTheme.typography.labelLarge)
            Text(movie.title.orEmpty(), style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Genre", style = MaterialTheme.typography.labelLarge)
            Text(movie.genre.orEmpty(), style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListItemPreview() {
    MovieListItem(
        Movie("Genre", "Title")
    )
}