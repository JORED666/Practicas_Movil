package com.project.examenprctico.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.project.examenprctico.ui.PokemonViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: PokemonViewModel
) {
    val favorites by viewModel.favorites.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Favoritos") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            if (favorites.isEmpty()) {
                Text("No hay favoritos aún.")
                return@Column
            }

            LazyColumn {
                items(favorites) { fav ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                navController.navigate("detail/${fav.name}")
                            }
                    ) {

                        AsyncImage(
                            model = fav.imageUrl,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = fav.name.uppercase(),
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(
                            onClick = {
                                viewModel.removeFavorite(fav.name)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Eliminar"
                            )
                        }
                    }
                }
            }
        }
    }
}
