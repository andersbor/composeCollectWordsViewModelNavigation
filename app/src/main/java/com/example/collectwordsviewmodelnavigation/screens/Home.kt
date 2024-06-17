package com.example.collectwordsviewmodelnavigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.collectwordsviewmodelnavigation.WordsViewModel

@Composable
fun Home(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: WordsViewModel = viewModel()
) {
    CollectWords(navController, modifier = modifier, viewModel = viewModel)
}

@Composable
fun CollectWords(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: WordsViewModel
) {
    // Add to gradle file
    // https://tigeroakes.com/posts/mutablestateof-list-vs-mutablestatelistof/
    val words = viewModel.words.observeAsState()
    var word by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(true) }
    val delete: (String) -> Unit = { viewModel.remove(it) }

    Column(modifier = modifier) {
        Text(text = "Collect words", style = MaterialTheme.typography.headlineLarge)
        OutlinedTextField(
            value = word,
            onValueChange = { word = it },
            // https://medium.com/@GkhKaya00/exploring-keyboard-types-in-kotlin-jetpack-compose-ca1f617e1109
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter a word") }
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                viewModel.add(word)
            }) {
                Text("Add")
            }
            Button(onClick = {
                viewModel.clear()
                word = ""
                result = ""
            }) {
                Text("Clear")
            }
            Button(onClick = { result = viewModel.toString() }) {
                Text("Show")
            }
            Button(onClick = { navController.navigate("Show") }) {
                Text("Show other")
            }
        }
        if (result.isNotEmpty()) {
            Text(result)
        } else {
            Text("Empty", fontStyle = FontStyle.Italic)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show list")
            Spacer(modifier = Modifier.padding(5.dp))
            Switch(checked = showList, onCheckedChange = { showList = it })
        }
        if (showList) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(words.value ?: emptyList()) { wo ->
                    Text(wo, modifier = Modifier.clickable { viewModel.remove(wo) })
                }
            }
        }
    }
}