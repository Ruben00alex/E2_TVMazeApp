package com.example.examen2_android.viewmodel

import android.util.Log
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

    private val _searchResults = MutableStateFlow<List<Show>>(emptyList())
    val searchResults: StateFlow<List<Show>> = _searchResults

    private val _favoriteShows = MutableStateFlow<List<ShowEntity>>(emptyList())
    val favoriteShows: StateFlow<List<ShowEntity>> = _favoriteShows

    private val _showDetails = MutableStateFlow<Show?>(null)
    val showDetails: StateFlow<Show?> = _showDetails

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    init {
        getFavoriteShows() // Fetch favorites initially
        getShows()
    }
    fun getShows() {
        viewModelScope.launch {
            try {
                _shows.value = repository.getShows()
                _errorMessage.value = null
            } catch (e: Exception) {
                Log.d("ShowViewModel", "Failed to fetch shows", e)
                _errorMessage.value = "Failed to fetch shows. Please check your internet connection."
            }
        }
    }

    fun searchShows(query: String) {
        viewModelScope.launch {
            try {
                _searchResults.value = repository.searchShows(query)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to search shows. Please check your internet connection."
            }
        }
    }

    fun getShowDetails(id: Int) {
        viewModelScope.launch {
            try {
                val show = repository.getShowDetails(id)
                _showDetails.value = show
                _isFavorite.value = repository.isFavoriteShow(id)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Failed to fetch show details. Please check your internet connection."
            }
        }
    }

    fun saveShow(show: ShowEntity) {
        viewModelScope.launch {
            repository.saveShow(show)
            _isFavorite.value = true
            getFavoriteShows()
        }
    }

    fun deleteShow(id: Int) {
        viewModelScope.launch {
            repository.deleteShow(id)
            _isFavorite.value = false
            getFavoriteShows()
        }
    }
    fun getFavoriteShows() {
        viewModelScope.launch {
            _favoriteShows.value = repository.getFavoriteShows()
        }
    }

}