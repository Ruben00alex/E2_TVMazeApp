package com.example.examen2_android.ui.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examen2_android.model.Show


@Composable
fun ShowItem(show: Show) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = show.name, style = MaterialTheme.typography.titleMedium)
        Text(text = show.genres.joinToString(", "), style = MaterialTheme.typography.bodyMedium)
        show.rating?.average?.let {
            Text(text = "Rating: $it", style = MaterialTheme.typography.bodySmall)
        }
    }
}