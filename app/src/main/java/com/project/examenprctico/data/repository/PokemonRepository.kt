package com.project.examenprctico.data.repository

import com.project.examenprctico.data.local.FavoriteDao
import com.project.examenprctico.data.local.FavoriteEntity
import com.project.examenprctico.data.remote.PokemonApi
import com.project.examenprctico.data.remote.RetrofitClient

class PokemonRepository(
    private val favoriteDao: FavoriteDao
) {

    private val api: PokemonApi = RetrofitClient.api

    suspend fun getPokemonList() =
        api.getPokemonList().results

    suspend fun getPokemonDetail(name: String) =
        api.getPokemonByName(name)

    suspend fun addFavorite(name: String, imageUrl: String?) {
        favoriteDao.insertFavorite(FavoriteEntity(name, imageUrl))
    }

    suspend fun removeFavorite(name: String) {
        val fav = favoriteDao.getFavoriteByName(name)
        if (fav != null) favoriteDao.deleteFavorite(fav)
    }

    suspend fun isFavorite(name: String): Boolean {
        return favoriteDao.getFavoriteByName(name) != null
    }

    suspend fun getFavorites(): List<FavoriteEntity> {
        return favoriteDao.getFavorites()
    }
}
