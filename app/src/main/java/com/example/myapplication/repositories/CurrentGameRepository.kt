package com.example.myapplication.repositories

import com.example.myapplication.domain.SudokuBoard
import com.example.myapplication.domain.SudokuGenerator
import javax.inject.Inject

class CurrentGameRepository @Inject constructor(
        private val sudokuGenerator: SudokuGenerator
) {

    private var sudokuBoard: SudokuBoard? = null

    fun saveGame(sudokuBoard: SudokuBoard) {
        this.sudokuBoard = sudokuBoard
        // TODO: Persist between sessions
    }

    suspend fun fetchGame(): SudokuBoard {
        return sudokuBoard ?: sudokuGenerator.createGame()
    }
}