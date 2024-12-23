package com.example.note_tab.ui.util

import android.app.Application

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
    }
}