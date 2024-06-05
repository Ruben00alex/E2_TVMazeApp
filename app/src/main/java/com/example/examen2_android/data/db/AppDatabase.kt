package com.example.examen2_android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ShowEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao
}
