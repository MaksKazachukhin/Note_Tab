package com.example.note_tab.ui.dp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note_tab.ui.dp.daos.NoteDao

@Database(entities = [], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}


