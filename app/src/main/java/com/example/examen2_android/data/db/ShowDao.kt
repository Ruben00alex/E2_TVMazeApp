package com.example.examen2_android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: ShowEntity)

    @Query("SELECT * FROM shows")
    suspend fun getFavoriteShows(): List<ShowEntity>

    @Query("DELETE FROM shows WHERE id = :showId")
    suspend fun deleteShow(showId: Int)

    @Query("SELECT * FROM shows WHERE id = :showId LIMIT 1")
    suspend fun getShowById(showId: Int): ShowEntity?
}
