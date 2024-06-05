package com.example.examen2_android.ui.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.examen2_android.ui.view.composables.SearchBar
import com.example.examen2_android.ui.view.composables.ShowItem
import com.example.examen2_android.viewmodel.ShowViewModel
import kotlinx.coroutines.launch


@Composable
fun SearchScreen(viewModel: ShowViewModel) {
    val scope = rememberCoroutineScope()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val searchResults by viewModel.shows.collectAsState()

    Column {
        SearchBar(
            query = searchText,
            onQueryChange = { searchText = it },
            onSearch = {
                scope.launch {
                    viewModel.searchShows(searchText.text)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn {
            items(searchResults) { show ->
                ShowItem(show)
            }
        }
    }
}