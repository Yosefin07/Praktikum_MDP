package com.example.praktikum_mdp.model.response


data class NoteItem(
    val id_notes: String,
    val id_user: String,
    val nm_lengkap: String,
    val title: String,
    val content: String,
    val created_at: String,
    val updated_at: String
)
