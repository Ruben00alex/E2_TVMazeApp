package com.example.examen2_android.ui.view.screens

import android.content.Intent
import android.net.Uri
import android.text.util.Linkify
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.rememberImagePainter
import com.example.examen2_android.model.Show
import com.example.examen2_android.ui.view.composables.ShowActorCard
import com.google.android.material.textview.MaterialTextView


@Composable
fun DetailScreen(
    show: Show, onBack: () -> Unit, onAddToFavorites: () -> Unit,
    isFavorite: Boolean
) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .fillMaxSize()
    ) {
        item {
            Box {
                val painter = rememberImagePainter(data = show.image?.original)
                Image(
                    painter = painter,
                    contentDescription = "Show Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    IconButton(onClick = {
                        onAddToFavorites()
                    }, modifier = Modifier.background(Color.Black.copy(alpha = 0.5f))) {
                        Icon(
                            if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                            tint = if (isFavorite) Color.Red else Color.White
                        )
                    }
                }
            }
        }
        item {
            Text(
                text = show.name,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(0.dp, 16.dp)
            )

            show.premiered?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }

            show.genres?.let {
                Text(
                    text = it.joinToString(", "),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            show.rating?.average?.let { rating ->
                Text(text = "Rating: $rating", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }

            show.summary?.let {
                val spannedText = HtmlCompat.fromHtml(show.summary, 0)
                AndroidView(
                    modifier = Modifier,
                    factory = {
                        MaterialTextView(it).apply {
                            // links
                            autoLinkMask = Linkify.WEB_URLS
                            linksClickable = true
                            // setting the color to use forr highlihting the links
                            setLinkTextColor(Color.White.toArgb());


                        }
                    },
                    update = {
                        it.text = spannedText
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            Text(text = "Cast", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

        }
        item {
           if( show.embedded?.cast?.isNotEmpty() == true){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(600.dp)
                        .padding(horizontal = 16.dp)

                ) {
                    items(show.embedded.cast ) { castMember ->
                        val context = LocalContext.current
                        ShowActorCard(castMember) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            context.startActivity(intent)
                        }
                    }
                }

            }else{ Text(text = "No cast available")}

        }
    }
}