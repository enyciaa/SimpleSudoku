package com.example.myapplication.ui

import android.widget.Toast
import com.example.myapplication.di.ActivityScope
import com.example.myapplication.domain.Announcer
import javax.inject.Inject

@ActivityScope
class AnnouncerImpl@Inject constructor(
        private val sudokuActivity: SudokuActivity
): Announcer {

    override fun announce(text: String) {
        Toast.makeText(sudokuActivity, text, Toast.LENGTH_LONG).show()
    }
}