package com.example.examen2_android
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.examen2_android.viewmodel.ShowViewModel
import kotlinx.coroutines.launch
import com.example.examen2_android.ui.theme.Examen2_AndroidTheme
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.examen2_android.data.db.AppDatabase
import com.example.examen2_android.data.repository.ShowRepository
import com.example.examen2_android.model.Show
import com.example.examen2_android.ui.view.composables.LoadingScreen
import com.example.examen2_android.ui.view.composables.ShowActorCard
import com.example.examen2_android.ui.view.screens.DetailScreen
import com.example.examen2_android.viewmodel.ShowViewModelFactory

class DetailActivity : ComponentActivity() {

    private lateinit var showViewModel: ShowViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val showId = intent.getIntExtra("showId", -1)
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "show_db").build()
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
                        val show by showViewModel.showDetails.collectAsState()
                        show?.let { DetailScreen(it) } ?: LoadingScreen()

                }
            }
        }
    }
}