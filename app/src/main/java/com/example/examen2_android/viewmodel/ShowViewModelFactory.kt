package com.example.examen2_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examen2_android.data.repository.ShowRepository

class ShowViewModelFactory(private val repository: ShowRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShowViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ShowViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}