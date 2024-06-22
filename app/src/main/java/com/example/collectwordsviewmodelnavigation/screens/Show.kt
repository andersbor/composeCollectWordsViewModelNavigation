package com.example.collectwordsviewmodelnavigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.collectwordsviewmodelnavigation.WordsViewModel

@Composable
fun Show(
 /*   navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: WordsViewModel = viewModel()
) {
    ShowWords(words = viewModel.words.value,
        modifier = modifier,
        onRemove = { word -> viewModel.remove(word) },
        onNavigate = { navController.popBackStack() }
    )
}

@Composable
fun ShowWords(*/
    words: List<String>?,
    modifier: Modifier = Modifier,
    onRemove: (String) -> Unit = { },
    onNavigate: () -> Unit = { null },
) {
    //val words: State<List<String>?> = viewModel.words.observeAsState()
    Column(modifier = modifier) {
        Text(text = "Words", style = MaterialTheme.typography.headlineLarge)
        Button(onClick = { onNavigate() }) {
            Text("Go back")
        }
        if (words.isNullOrEmpty()) {
            Text("No words")
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(words) { word ->
                    Text(word, modifier = Modifier.clickable { onRemove(word) })
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowPreview() {
    Show(words = listOf("Hello", "World"))
}
