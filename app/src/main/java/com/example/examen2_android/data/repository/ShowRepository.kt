package com.example.examen2_android.data.repository

import android.util.Log
import com.example.examen2_android.data.api.RetrofitInstance
import com.example.examen2_android.data.db.ShowDao
import com.example.examen2_android.data.db.ShowEntity
import com.example.examen2_android.model.Show

class ShowRepository(private val showDao: ShowDao) {

    suspend fun getShows(): List<Show> {
        return try {
            RetrofitInstance.api.getShows()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun searchShows(query: String): List<Show> {
        return try {
            RetrofitInstance.api.searchShows(query).map { it.show }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getShowDetails(id: Int): Show? {
        return try {
            RetrofitInstance.api.getShowDetails(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun saveShow(show: ShowEntity) {
        showDao.insertShow(show)
    }

    suspend fun getFavoriteShows(): List<ShowEntity> {
        return showDao.getFavoriteShows()
    }

    suspend fun deleteShow(id: Int) {
        showDao.deleteShow(id)
    }

    suspend fun isFavoriteShow(id: Int): Boolean {
        return showDao.getShowById(id) != null
    }
}
