package com.example.praktikum_mdp.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum_mdp.Components.NoteCard
import com.example.praktikum_mdp.model.response.NoteItem

@Composable
fun NotesScreen(notes: List<NoteItem>) {
    Column(
        modifier = Modifier
            .fillMaxSize() // ⬅️ Ini membuat layar penuh
            .padding(16.dp)
    ) {
        Text(
            text = "📒 Daftar Notes",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ) {
            items(notes) { note ->
                NoteCard(note = note)
            }
        }
    }
}

