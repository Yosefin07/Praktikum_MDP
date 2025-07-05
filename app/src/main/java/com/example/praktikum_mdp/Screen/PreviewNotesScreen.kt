package com.example.praktikum_mdp.Screen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.praktikum_mdp.model.response.NoteItem
import com.example.praktikum_mdp.Screen.NotesScreen

@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    val dummyNotes = listOf(
        NoteItem(
            id_notes = "1",
            id_user = "user1",
            nm_lengkap = "Yosefin",
            title = "✏️ Belajar Compose",
            content = "Belajar membuat UI dengan Jetpack Compose.",
            created_at = "",
            updated_at = ""
        ),
        NoteItem(
            id_notes = "2",
            id_user = "user2",
            nm_lengkap = "Admin",
            title = "🔐 Authentication",
            content = "Membahas tentang JWT dan login session.",
            created_at = "",
            updated_at = ""
        )
    )

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEEEAFE) // ungu muda
    ) {
        NotesScreen(notes = dummyNotes)
    }
}