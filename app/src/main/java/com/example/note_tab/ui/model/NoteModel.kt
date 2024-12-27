package com.example.note_tab.ui.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteModel")
data class NoteModel(
    val title: String,
    val description: String,
    val date: String,
    val color: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
