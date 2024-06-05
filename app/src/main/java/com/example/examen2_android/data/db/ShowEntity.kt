package com.example.examen2_android.data.db
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val genres: String,
    val rating: Double,
    val thumbnail: String
)