package com.example.examen2_android


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.examen2_android.data.db.AppDatabase
import com.example.examen2_android.data.repository.ShowRepository
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
        showViewModel.getShows()
        setContent {
            Examen2_AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val showList by showViewModel.shows.collectAsState()
                    MainScreen(showList = showList)

            }
        }
    }
}

}





@Composable
fun FavoritesScreen() {
}

