package com.project.examenprctico.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.project.examenprctico.MyApp
import com.project.examenprctico.data.local.AppDatabase
import com.project.examenprctico.data.local.FavoriteEntity
import com.project.examenprctico.data.remote.PokemonDetailResponse
import com.project.examenprctico.data.remote.PokemonResult
import com.project.examenprctico.data.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val db = Room.databaseBuilder(
        MyApp.applicationContext,     // ← AHORA SÍ funciona
        AppDatabase::class.java,
        "pokemon-db"
    ).build()

    private val repository = PokemonRepository(db.favoriteDao())

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList: StateFlow<List<PokemonResult>> = _pokemonList

    val searchQuery = MutableStateFlow("")

    private val _selectedPokemon = MutableStateFlow<PokemonDetailResponse?>(null)
    val selectedPokemon: StateFlow<PokemonDetailResponse?> = _selectedPokemon

    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteEntity>> = _favorites

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    init {
        loadPokemonList()
        loadFavorites()
    }

    fun loadPokemonList() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _pokemonList.value = repository.getPokemonList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            _selectedPokemon.value = repository.getPokemonDetail(name)
            _isFavorite.value = repository.isFavorite(name)
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getFavorites()
        }
    }

    fun toggleFavorite() {
        val pokemon = _selectedPokemon.value ?: return
        viewModelScope.launch {
            if (_isFavorite.value) {
                repository.removeFavorite(pokemon.name)
                _isFavorite.value = false
            } else {
                repository.addFavorite(pokemon.name, pokemon.sprites.frontDefault)
                _isFavorite.value = true
            }
            loadFavorites()
        }
    }

    fun removeFavorite(name: String) {
        viewModelScope.launch {
            repository.removeFavorite(name)
            loadFavorites()
        }
    }
}
