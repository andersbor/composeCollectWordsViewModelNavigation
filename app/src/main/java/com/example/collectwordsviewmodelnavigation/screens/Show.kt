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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.collectwordsviewmodelnavigation.WordsViewModel

@Composable
fun Show(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: WordsViewModel = viewModel()
) {
    val words = viewModel.words.observeAsState()
    Column(modifier = modifier) {
        Text(text = "Words", style = MaterialTheme.typography.headlineLarge)
        Button(onClick = { navController.popBackStack() }) {
            Text("Go back")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(words.value ?: emptyList()) { wo ->
                Text(wo, modifier = Modifier.clickable { viewModel.remove(wo) })
            }
        }
    }
}
