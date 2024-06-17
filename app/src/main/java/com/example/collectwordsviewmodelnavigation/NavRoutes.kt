package com.example.collectwordsviewmodelnavigation

sealed class NavRoutes(val route: String) {
    data object Home : NavRoutes("home")
    data object Show : NavRoutes("show")
}