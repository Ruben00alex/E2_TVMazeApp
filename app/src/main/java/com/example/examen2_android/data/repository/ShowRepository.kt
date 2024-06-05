package com.example.examen2_android.data.repository

import android.util.Log
import com.example.examen2_android.data.api.RetrofitInstance
import com.example.examen2_android.data.db.ShowDao
import com.example.examen2_android.data.db.ShowEntity
import com.example.examen2_android.model.Show

class ShowRepository(private val showDao: ShowDao) {

    suspend fun getShows(): List<Show> {
        return RetrofitInstance.api.getShows()
    }

    suspend fun searchShows(query: String): List<Show> {
        return RetrofitInstance.api.searchShows(query).map { it.show }
    }

    suspend fun getShowDetails(id: Int): Show {
        return RetrofitInstance.api.getShowDetails(id)
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
}
