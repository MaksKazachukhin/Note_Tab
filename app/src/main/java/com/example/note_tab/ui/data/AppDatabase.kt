package com.example.note_tab.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note_tab.ui.data.daos.NoteDao
import com.example.note_tab.ui.model.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}