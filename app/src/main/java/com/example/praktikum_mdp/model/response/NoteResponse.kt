package com.example.praktikum_mdp.model.response


data class NotesResponse(
    val code: Int,
    val message: String,
    val data: NotesData
)

data class NotesData(
    val notes: List<NoteItem>
)
