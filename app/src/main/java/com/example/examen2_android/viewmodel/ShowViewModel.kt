package com.example.examen2_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen2_android.data.db.ShowEntity
import com.example.examen2_android.data.repository.ShowRepository
import com.example.examen2_android.model.Show
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ShowViewModel(private val repository: ShowRepository) : ViewModel() {

    private val _shows = MutableStateFlow<List<Show>>(emptyList())
    val shows: StateFlow<List<Show>> = _shows

    private val _favoriteShows = MutableStateFlow<List<ShowEntity>>(emptyList())
    val favoriteShows: StateFlow<List<ShowEntity>> = _favoriteShows

    private val _showDetails = MutableStateFlow<Show?>(null)
    val showDetails: StateFlow<Show?> = _showDetails
    fun getShows() {
        viewModelScope.launch {
            _shows.value = repository.getShows()
        }
    }

    fun searchShows(query: String) {
        viewModelScope.launch {
            _shows.value = repository.searchShows(query)
        }
    }

    fun getShowDetails(id: Int) {
        viewModelScope.launch {
            val show = repository.getShowDetails(id)
            _showDetails.value = show
        }
    }
    suspend fun returnShowDetails(id: Int):Show {
         return  repository.getShowDetails(id)

    }

    fun saveShow(show: ShowEntity) {
        viewModelScope.launch {
            repository.saveShow(show)
            getFavoriteShows()
        }
    }

    fun getFavoriteShows() {
        viewModelScope.launch {
            _favoriteShows.value = repository.getFavoriteShows()
        }
    }

    fun deleteShow(id: Int) {
        viewModelScope.launch {
            repository.deleteShow(id)
            getFavoriteShows()
        }
    }
}