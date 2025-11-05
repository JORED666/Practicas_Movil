package com.edmonbl.cuartitocapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.edmonbl.cuartitocapp.R
import com.edmonbl.cuartitocapp.students

@Composable
fun StudentDetailScreen(navController: NavController, studentId: Int?) {
    val student = students.find { it.id == studentId }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (student != null) {
            Text(text = "Student Details", modifier = Modifier.padding(bottom = 16.dp))
            val painter = rememberAsyncImagePainter(model = student.imageUrl)

            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    is AsyncImagePainter.State.Error -> {
                        Text(
                            text = "Image failed to load",
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    else -> {
                        Image(
                            painter = painter,
                            contentDescription = "Student Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${student.name}")
            Text(text = "Description: ${student.description}")
        } else {
            Text(text = "Student not found")
        }
    }
}
