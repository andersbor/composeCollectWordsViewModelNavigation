package com.example.collectwordsviewmodelnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.collectwordsviewmodelnavigation.screens.Home
import com.example.collectwordsviewmodelnavigation.screens.Show
import com.example.collectwordsviewmodelnavigation.ui.theme.CollectWordsViewModelNavigationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollectWordsViewModelNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = { Text("Collect words") })
                    }) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // Inspiration from https://medium.com/@khambhaytajaydip/creating-sharedviewmodel-in-android-98ed4aceb7dd
    val viewModel: WordsViewModel = viewModel() // persistence

    val words = viewModel.words.observeAsState().value
    // Add to gradle file
    // https://tigeroakes.com/posts/mutablestateof-list-vs-mutablestatelistof/

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            Home(modifier = modifier,
                words = words,
                onAdd = { word -> viewModel.add(word) },
                onRemove = { word -> viewModel.remove(word) },
                onClear = { viewModel.clear() },
                onNavigate = { navController.navigate(NavRoutes.Show.route) })
        }
        composable(NavRoutes.Show.route) {
            Show(modifier = modifier,
                words = words,
                onRemove = { word -> viewModel.remove(word) },
                onNavigate = { navController.popBackStack() })
        }
    }
}