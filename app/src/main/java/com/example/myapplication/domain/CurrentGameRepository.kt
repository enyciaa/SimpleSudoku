package com.example.myapplication.domain

import javax.inject.Inject

class CurrentGameRepository @Inject constructor(
        private val sudokuGenerator: SudokuGenerator
) {

    private var sudokuGame: SudokuGame? = null

    fun saveGame(sudokuGame: SudokuGame) {
        this.sudokuGame = sudokuGame
    }

    fun fetchGame(): SudokuGame {
        return sudokuGame ?: sudokuGenerator.createGame()
    }
}