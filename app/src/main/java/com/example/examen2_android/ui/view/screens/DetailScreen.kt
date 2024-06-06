package com.example.examen2_android.ui.view.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.examen2_android.model.Show
import com.example.examen2_android.ui.view.composables.LoadingScreen
import com.example.examen2_android.ui.view.composables.ShowActorCard


@Composable
fun DetailScreen(show: Show) {



    show.let {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = it.name, style = MaterialTheme.typography.bodySmall)
            Text(text = it.genres.joinToString(", "), style = MaterialTheme.typography.bodyMedium)
            it.rating?.average?.let { rating ->
                Text(text = "Rating: $rating", style = MaterialTheme.typography.bodyMedium)
            }
//            Text(text = it.summary, style = MaterialTheme.typography.bodyMedium)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(it.embedded?.cast ?: emptyList()) { castMember ->

                    val context = LocalContext.current
                    ShowActorCard(castMember){
                        //intent to open the actor detail screen on a web browser:
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)

                    }

                }
            }
        }
    }
}