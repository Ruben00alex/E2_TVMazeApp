package com.example.examen2_android
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.examen2_android.data.db.AppDatabase
import com.example.examen2_android.data.repository.ShowRepository
import com.example.examen2_android.model.Image
import com.example.examen2_android.model.Rating
import com.example.examen2_android.model.Show
import com.example.examen2_android.ui.theme.Examen2_AndroidTheme
import com.example.examen2_android.ui.view.screens.MainScreen
import com.example.examen2_android.viewmodel.ShowViewModel
import com.example.examen2_android.viewmodel.ShowViewModelFactory

class DashboardActivity : ComponentActivity() {
    private lateinit var showViewModel: ShowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "show_db").build()
        val repository = ShowRepository(database.showDao())


        val factory = ShowViewModelFactory(repository)
        showViewModel = ViewModelProvider(this, factory)[ShowViewModel::class.java]

        setContent {
            Examen2_AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val showList by showViewModel.shows.collectAsState()
                    val favoritesList by showViewModel.favoriteShows.collectAsState()
                    val errorMessage by showViewModel.errorMessage.collectAsState()

                    val favoriteShows = favoritesList.map { showEntity ->
                        Show(
                            id = showEntity.id,
                            name = showEntity.name,
                            genres = showEntity.genres.split(", "),
                            rating = Rating(showEntity.rating),
                            image = Image(showEntity.thumbnail, showEntity.thumbnail),
                            premiered = "",
                            language = "",
                            summary = "",
                            network = null,
                            embedded = null
                        )
                    }
                    Column {
                        if (errorMessage != null) {
                            Log.d("DashboardActivity", "Error: $errorMessage")
                            Text(
                                text = errorMessage!!,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        MainScreen(showList = showList, favoritesList = favoriteShows, showViewModel)
                    }
            }
        }
    }
}

    override fun onResume() {
        super.onResume()
        showViewModel.getFavoriteShows()
    }

}



