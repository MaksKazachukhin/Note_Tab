package com.example.note_tab.ui.Models

import androidx.room.Entity

@Entity(tableName = "noteModel")
data class Note(
    val id: Int,
    val title: String,
    val description: String
)
