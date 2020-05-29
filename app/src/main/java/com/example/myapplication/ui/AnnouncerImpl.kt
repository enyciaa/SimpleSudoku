package com.example.myapplication.ui

import com.example.myapplication.di.ActivityScope
import com.example.myapplication.domain.Announcer
import com.example.myapplication.getRootViewGroup
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

@ActivityScope
class AnnouncerImpl @Inject constructor(
        private val sudokuActivity: SudokuActivity
) : Announcer {

    override fun announce(
            text: String,
            actionText: String,
            callback: () -> Unit
    ) {
        Snackbar.make(sudokuActivity.rootView, text, Snackbar.LENGTH_LONG)
                .setAction(actionText) { callback() }
                .show()
    }
}