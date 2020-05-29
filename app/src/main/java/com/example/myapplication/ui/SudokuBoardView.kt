package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import com.example.myapplication.R
import com.example.myapplication.ui.base.MotherView
import kotlinx.android.synthetic.main.sudoku_board_view.view.*

class SudokuBoardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : MotherView(
        context,
        attrs,
        defStyleAttr
) {

    private lateinit var sudokuBoard: Array<Array<SudokuSquareView>>

    init {
        inflate(getContext(), R.layout.sudoku_board_view, this)
        inflateBoard()
    }

    private fun inflateBoard() {
        sudokuBoard = Array(3) {
            Array(3) {
                val sudokuSquare = SudokuSquareView(context)
                sudokuBoardLayout.addView(sudokuSquare)
                sudokuSquare
            }
        }
    }
}