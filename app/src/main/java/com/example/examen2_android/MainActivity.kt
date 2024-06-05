package com.example.examen2_android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.examen2_android.data.db.AppDatabase
import com.example.examen2_android.data.repository.ShowRepository
import com.example.examen2_android.ui.theme.Examen2_AndroidTheme
import com.example.examen2_android.ui.view.screens.MainScreen
import com.example.examen2_android.viewmodel.ShowViewModel
import com.example.examen2_android.viewmodel.ShowViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() { private lateinit var showViewModel: ShowViewModel

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "show_db").build()
        val repository = ShowRepository(database.showDao())


        val factory = ShowViewModelFactory(repository)
        showViewModel = ViewModelProvider(this, factory).get(ShowViewModel::class.java)



        setContent {
            Examen2_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
