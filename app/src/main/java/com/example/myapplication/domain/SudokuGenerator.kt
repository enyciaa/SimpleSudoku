package com.example.myapplication.domain

import javax.inject.Inject

class SudokuGenerator @Inject constructor() {

    fun createGame(): SudokuGame {
        return SudokuGame("Game")
    }
}