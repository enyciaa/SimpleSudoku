package com.example.myapplication.domain

import java.lang.RuntimeException
import javax.inject.Inject

class SudokuBoardEditor @Inject constructor() {

    private var sudokuBoard: SudokuBoard? = null

    fun setSudokuBoard(sudokuBoard: SudokuBoard) {
        this.sudokuBoard = sudokuBoard
    }

    fun updateNumber(number: Int, cell: Pair<Int, Int>): SudokuBoard {
        if (sudokuBoard == null) {
            throw RuntimeException("Board need initialised")
        }
        val editableCell = sudokuBoard!!.getCell(cell.first, cell.second)
        editableCell.value = number
        return sudokuBoard!!
    }

    fun getBoard(): SudokuBoard? {
        return sudokuBoard
    }
}