package com.example.note_tab.ui.dp

import com.example.note_tab.ui.model.NoteModel


interface OnClickItem {
    fun onLongClick(noteModel: NoteModel)

    fun onClick(noteModel: NoteModel)
}