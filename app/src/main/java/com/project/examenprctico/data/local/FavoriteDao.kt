package com.project.examenprctico.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import androidx.room.OnConflictStrategy


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(fav: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(fav: FavoriteEntity)

    @Query("SELECT * FROM favorites WHERE name = :name LIMIT 1")
    suspend fun getFavoriteByName(name: String): FavoriteEntity?
}
