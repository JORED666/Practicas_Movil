package com.edmonbl.mobile.presentation.navManager

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.edmonbl.mobile.presentation.views.DetailsView
import com.edmonbl.mobile.presentation.views.HomeView

@Composable
fun NavManager(){
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "Home"
    ){
        composable("Home") {
            HomeView(navController)
        }
        composable("Details/{id}", arguments = listOf(
            navArgument(name="id"){
                type = NavType.LongType
            }
        )) {
            val id = it.arguments?.getLong("id") ?: 0
            DetailsView(navController, id)
        }
    }
}