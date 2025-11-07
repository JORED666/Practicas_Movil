package com.project.examenunidad2

import android.os.Bundle
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "settings")
private val THEME_KEY = booleanPreferencesKey("dark_theme")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppRoot() }
    }
}

@Composable
fun AppRoot() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isDarkTheme by context.dataStore.data
        .map { it[THEME_KEY] ?: false }
        .collectAsState(initial = false)

    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "users.db"
        ).fallbackToDestructiveMigration().build()
    }

    val setDarkTheme: (Boolean) -> Unit = { enabled ->
        scope.launch {
            context.dataStore.edit { prefs -> prefs[THEME_KEY] = enabled }
        }
    }

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Surface(
            modifier = androidx.compose.ui.Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val nav = rememberNavController()
            NavHost(navController = nav, startDestination = "dashboard") {
                composable("dashboard") {
                    DashboardScreen(
                        goTheme = { nav.navigate("theme") },
                        goUsers = { nav.navigate("users") }
                    )
                }
                composable("theme") {
                    ThemeScreen(
                        isDark = isDarkTheme,
                        onToggle = { setDarkTheme(!isDarkTheme) },
                        onBack = { nav.popBackStack() }
                    )
                }
                composable("users") {
                    UsersScreen(
                        db = db,
                        onBack = { nav.popBackStack() }
                    )
                }
            }
        }
    }
}
