package com.example.note_tab.ui.dp.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.note_tab.ui.Models.Note


@Dao
interface NoteDao {
    @Insert(OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: Note)
    @Query("SELECT * FROM noteModel")
    fun getAll():LiveData<Note>
}