package com.project.examenprctico.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.examenprctico.ui.PokemonViewModel



@Composable
fun HomeScreen(navController: NavHostController, viewModel: PokemonViewModel) {

    val list by viewModel.pokemonList.collectAsState()
    val query by viewModel.searchQuery.collectAsState()

    val filtered = remember(list, query) {
        if (query.isBlank()) list
        else list.filter { it.name.contains(query.trim(), ignoreCase = true) }
    }

    Column(modifier = Modifier.padding(16.dp)) {

        TextField(
            value = query,
            onValueChange = { viewModel.searchQuery.value = it },
            label = { Text("Buscar Pokémon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { navController.navigate("favorites") }) {
            Text("Ver Favoritos")
        }

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(filtered) { pokemon ->
                Text(
                    text = pokemon.name.uppercase(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detail/${pokemon.name}") }
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}
