package com.edmonbl.cuartitocapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edmonbl.cuartitocapp.ui.screens.AddStudentScreen
import com.edmonbl.cuartitocapp.ui.screens.DashboardScreen
import com.edmonbl.cuartitocapp.ui.screens.StudentDetailScreen
import com.edmonbl.cuartitocapp.ui.viewmodel.StudentViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val studentViewModel: StudentViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(navController, studentViewModel)
        }
        composable("details/{studentId}") { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString("studentId")?.toIntOrNull()
            StudentDetailScreen(navController, studentViewModel, studentId)
        }
        composable("add_student") {
            AddStudentScreen(navController, studentViewModel)
        }
    }
}
