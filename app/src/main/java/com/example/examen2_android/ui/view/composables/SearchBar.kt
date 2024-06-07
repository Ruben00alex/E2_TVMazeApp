package com.example.examen2_android.ui.view.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(8.dp), verticalAlignment = Alignment.CenterVertically){
        TextField(
            value = query,
            onValueChange = onQueryChange,
            label = { Text("Search TV Shows") },
            modifier = Modifier
                .padding(8.dp),
            singleLine = true
        )
        Button(
            onClick = onSearch,
            modifier = Modifier
        ) {
            Icon(Icons.Rounded.Search, contentDescription = "Localized description")
        }
    }
}
