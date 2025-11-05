package com.edmonbl.cuartitocapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edmonbl.cuartitocapp.students

@Composable
fun DashboardScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Dashboard", modifier = Modifier.padding(bottom = 16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(students) { student ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("details/${student.id}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = student.name, modifier = Modifier.weight(1f))
                    Text(text = "View Details")
                }
            }
        }
    }
}
