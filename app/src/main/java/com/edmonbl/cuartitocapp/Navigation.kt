package com.edmonbl.cuartitocapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edmonbl.cuartitocapp.ui.screens.DashboardScreen
import com.edmonbl.cuartitocapp.ui.screens.StudentDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(navController)
        }
        composable("details/{studentId}") { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")?.toIntOrNull()
            StudentDetailScreen(navController, studentId)
        }
    }
}
