package com.edmonbl.cuartitocapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.edmonbl.cuartitocapp.ui.viewmodel.StudentViewModel

@Composable
fun StudentDetailScreen(navController: NavController, studentViewModel: StudentViewModel, studentId: Int?) {
    val students by studentViewModel.studentsUiState.collectAsState()
    val student = students.find { it.id == studentId }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        if (student != null) {
            Text(text = "Student Details", modifier = Modifier.padding(bottom = 16.dp))
            AsyncImage(
                model = student.imageUrl,
                contentDescription = student.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${student.name}")
            Text(text = "Description: ${student.description}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                studentViewModel.deleteStudent(student)
                navController.popBackStack()
            }) {
                Text("Delete Student")
            }
        } else {
            Text(text = "Student not found")
        }
    }
}
