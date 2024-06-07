package com.example.examen2_android.ui.view.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examen2_android.DetailActivity
import com.example.examen2_android.model.Country
import com.example.examen2_android.model.Image
import com.example.examen2_android.model.Network
import com.example.examen2_android.model.Rating
import com.example.examen2_android.model.Show
import com.example.examen2_android.ui.view.composables.LoadingScreen
import com.example.examen2_android.ui.view.composables.ShowListCard


@Composable
fun ShowListScreen(showList: List<Show> = emptyList(), text: String = "Shows") {

    Column(modifier = Modifier.padding(4.dp, 16.dp)) {

        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)

        )
        Spacer(modifier = Modifier.padding(8.dp))

        if (showList.isEmpty()) {
            if (text == "Favorites") {
                Text(text = "No favorites yet")
            } else {
                LoadingScreen()
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(showList) { show ->

                val context = LocalContext.current
                ShowListCard(show) {

                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra("showId", show.id)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }
}


@Preview
@Composable
fun ShowListScreenPreview() {
    val show =
        Show(
            id = 1,
            name = "Under the Dome",
            genres = listOf("Drama", "Science-Fiction", "Thriller"),
            rating = Rating(average = 6.5),
            image = Image(
                medium = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
                original = "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
            ),
            premiered = "2013-06-24",
            language = "English",
            summary = "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
            network = Network(
                name = "CBS",
                country = Country(
                    name = "United States",
                    code = "US",
                    timezone = "America/New_York"
                )
            ),
        )

    val shows = listOf(show, show, show, show, show, show, show, show, show, show)
    ShowListScreen(showList = shows)
}

@Preview
@Composable
fun ShowListCardPreview() {
    val show =
        Show(
            id = 1,
            name = "Under the Dome",
            genres = listOf("Drama", "Science-Fiction", "Thriller"),
            rating = Rating(average = 6.5),
            image = Image(
                medium = "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg",
                original = "https://static.tvmaze.com/uploads/images/original_untouched/81/202627.jpg"
            ),
            premiered = "2013-06-24",
            language = "English",
            summary = "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>",
            network = Network(
                name = "CBS",
                country = Country(
                    name = "United States",
                    code = "US",
                    timezone = "America/New_York"
                )
            ),

            )

    ShowListCard(show = show)
}
