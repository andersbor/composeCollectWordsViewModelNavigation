package com.example.collectwordsviewmodelnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.collectwordsviewmodelnavigation.screens.Home
import com.example.collectwordsviewmodelnavigation.screens.Show
import com.example.collectwordsviewmodelnavigation.ui.theme.CollectWordsViewModelNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollectWordsViewModelNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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

    NavHost(navController = navController, startDestination = NavRoutes.Home.route) {
        composable(NavRoutes.Home.route) {
            Home(modifier = modifier, navController = navController, viewModel = viewModel)
        }
        composable(NavRoutes.Show.route) {
            Show(modifier = modifier, navController = navController, viewModel = viewModel)
        }
    }
}