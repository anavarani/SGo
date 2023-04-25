package com.varani.sgo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.varani.sgo.ui.theme.SGoTheme

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
fun SearchContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Search Content")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SGoTheme {
        SGoApp()
    }
}