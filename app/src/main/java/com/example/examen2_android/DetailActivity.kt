package com.example.examen2_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.examen2_android.data.db.AppDatabase
import com.example.examen2_android.data.db.ShowEntity
import com.example.examen2_android.data.repository.ShowRepository
import com.example.examen2_android.ui.theme.Examen2_AndroidTheme
import com.example.examen2_android.ui.view.composables.LoadingScreen
import com.example.examen2_android.ui.view.screens.DetailScreen
import com.example.examen2_android.viewmodel.ShowViewModel
import com.example.examen2_android.viewmodel.ShowViewModelFactory

class DetailActivity : ComponentActivity() {
    private lateinit var showViewModel: ShowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val showId = intent.getIntExtra("showId", -1)
        val database =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "show_db").build()
        val repository = ShowRepository(database.showDao())

        setContent {
            Examen2_AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val factory = ShowViewModelFactory(repository)
                    showViewModel = ViewModelProvider(this, factory)[ShowViewModel::class.java]
                    showViewModel.getShowDetails(showId)
                    showViewModel.getFavoriteShows()
                    val show by showViewModel.showDetails.collectAsState()
                    val isFavorite by showViewModel.isFavorite.collectAsState()
                    show?.let {
                        DetailScreen(it,
                            onBack = { finish();
                                 },
                            onAddToFavorites = {
                                val showEntity = it.genres?.let { it1 ->
                                    ShowEntity(
                                        id = it.id,
                                        name = it.name,
                                        genres = it1.joinToString(", "),
                                        rating = it.rating?.average ?: 0.0,
                                        thumbnail = it.image?.medium ?: ""
                                    )
                                }
                                if (isFavorite) {
                                    showViewModel.deleteShow(it.id)
                                } else {
                                    if (showEntity != null) {
                                        showViewModel.saveShow(showEntity)
                                    }
                                }
                            },
                            isFavorite = isFavorite
                        )
                    } ?: LoadingScreen()

                }
            }
        }
    }
}