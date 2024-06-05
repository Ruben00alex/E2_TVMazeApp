package com.example.examen2_android.ui.view.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    //ALIGN VERTICALLY THE ROW ELEMENTS
    Row(modifier.fillMaxWidth().padding(8.dp), verticalAlignment = Alignment.CenterVertically){
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
            Text("🔎")
        }
    }
}
