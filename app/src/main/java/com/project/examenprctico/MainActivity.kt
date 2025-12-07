package com.project.examenprctico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.project.examenprctico.ui.AppNavHost
import com.project.examenprctico.ui.theme.ExamenPrácticoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ExamenPrácticoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    // Crea controlador de navegación
                    val navController = rememberNavController()

                    // Carga todo el sistema de pantallas
                    AppNavHost(navController)
                }
            }
        }
    }
}
