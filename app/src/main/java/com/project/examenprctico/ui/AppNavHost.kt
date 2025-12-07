package com.project.examenprctico.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.examenprctico.ui.screens.DetailScreen
import com.project.examenprctico.ui.screens.FavoritesScreen
import com.project.examenprctico.ui.screens.HomeScreen
import com.project.examenprctico.ui.screens.LoadingScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    // ViewModel general para toda la app
    val viewModel: PokemonViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "loading"
    ) {

        composable("loading") {
            LoadingScreen(navController, viewModel)
        }

        composable("home") {
            HomeScreen(navController, viewModel)
        }

        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            DetailScreen(navController, viewModel, name)
        }

        composable("favorites") {
            FavoritesScreen(navController, viewModel)
        }
    }
}
