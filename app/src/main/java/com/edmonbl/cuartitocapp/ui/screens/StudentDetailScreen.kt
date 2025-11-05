package com.edmonbl.cuartitocapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edmonbl.cuartitocapp.students

@Composable
fun StudentDetailScreen(navController: NavController, studentId: Int?) {
    val student = students.find { it.id == studentId }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (student != null) {
            Text(text = "Student Details", modifier = Modifier.padding(bottom = 16.dp))
            Text(text = "Name: ${student.name}")
            Text(text = "Description: ${student.description}")
        } else {
            Text(text = "Student not found")
        }
    }
}
