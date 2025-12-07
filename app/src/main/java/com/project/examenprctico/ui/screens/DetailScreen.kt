package com.project.examenprctico.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.project.examenprctico.ui.PokemonViewModel
import com.project.examenprctico.ui.theme.colorForPokemonType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: PokemonViewModel,
    name: String
) {
    val pokemon by viewModel.selectedPokemon.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()

    LaunchedEffect(name) {
        viewModel.loadPokemonDetail(name)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(name.uppercase()) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text("<")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleFavorite() }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite
                            else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorito"
                        )
                    }
                }
            )
        }
    ) { padding ->

        if (pokemon == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        val data = pokemon!!

        // ⭐ COLOR DINÁMICO SEGÚN EL TIPO ⭐
        val mainType = data.types.firstOrNull()?.type?.name ?: "normal"
        val backgroundColor = colorForPokemonType(mainType)

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen principal
                AsyncImage(
                    model = data.sprites.frontDefault,
                    contentDescription = null,
                    modifier = Modifier.size(180.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("ID: ${data.id}", style = MaterialTheme.typography.titleMedium)
                Text("Nombre: ${data.name.uppercase()}", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(20.dp))

                // Información básica
                Text("Altura: ${data.height / 10.0} m")
                Text("Peso: ${data.weight / 10.0} kg")

                Spacer(modifier = Modifier.height(20.dp))

                // Tipos
                Text("Tipos:", style = MaterialTheme.typography.titleMedium)
                Row {
                    data.types.forEach { type ->
                        AssistChip(
                            onClick = {},
                            label = { Text(type.type.name.uppercase()) },
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Habilidades
                Text("Habilidades:", style = MaterialTheme.typography.titleMedium)
                Column {
                    data.abilities.forEach { ability ->
                        Text("- ${ability.ability.name}")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Stats
                Text("Estadísticas:", style = MaterialTheme.typography.titleMedium)
                Column {
                    data.stats.forEach { stat ->
                        Text("${stat.stat.name.uppercase()}: ${stat.baseStat}")
                    }
                }
            }
        }
    }
}
