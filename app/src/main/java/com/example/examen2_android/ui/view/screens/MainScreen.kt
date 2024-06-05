package com.example.examen2_android.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.examen2_android.FavoritesScreen
import com.example.examen2_android.HomeScreen
import com.example.examen2_android.data.db.AppDatabase
import com.example.examen2_android.data.repository.ShowRepository
import com.example.examen2_android.viewmodel.ShowViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val database = Room.databaseBuilder(LocalContext.current, AppDatabase::class.java, "show_db").build()
    val repository = ShowRepository(database.showDao())


    var selectedIndex by remember { mutableStateOf(0) }
    val items = listOf("Home", "Search", "Favorites")

    Scaffold(
        bottomBar = {
            BottomNavigation(windowInsets = BottomNavigationDefaults.windowInsets) {
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(item) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    ) {
        when (selectedIndex) {
            0 -> HomeScreen()
            1 -> SearchScreen( ShowViewModel(repository) )
            2 -> FavoritesScreen()
        }
    }
}