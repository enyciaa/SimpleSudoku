package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.domain.SudokuBoard
import com.example.myapplication.forEachIndexed2D
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

    private lateinit var sudokuBoardViews: Array<Array<FrameLayout>>
    private var selectedCellRowNumber: Int = -1
    private var selectedCellColumnNumber: Int = -1

    init {
        inflate(getContext(), R.layout.sudoku_board_view, this)
        inflateBoard()
    }

    private fun inflateBoard() {
        sudokuBoardViews = Array(9) { rowNumber ->
            Array(9) { columnNumber ->
                val view = inflate(context, R.layout.sudoku_cell, null) as FrameLayout
                view.setOnClickListener {
                    selectedCellRowNumber = rowNumber
                    selectedCellColumnNumber = columnNumber
                }
                sudokuBoardLayout.addView(view)
                view
            }
        }
    }

    fun setBoard(sudokuBoard: SudokuBoard) {
        sudokuBoardViews.forEachIndexed2D { rowNumber, columnNumber, frameLayout ->
            val textView: TextView = frameLayout.findViewById(R.id.sudokuNumber)
            textView.text = sudokuBoard.getCell(rowNumber, columnNumber).value.ifZeroReturnNull().toStringOrEmpty()
        }
    }

    fun getSelectedCell(): Pair<Int, Int>? {
        if (selectedCellRowNumber < 0 || selectedCellColumnNumber < 0) {
            return null
        }
        return Pair(selectedCellRowNumber, selectedCellColumnNumber)
    }

    private fun Int.ifZeroReturnNull(): Int? {
        return if (this == 0) {
            null
        } else {
            this
        }
    }

    private fun Int?.toStringOrEmpty(): String {
        return this?.toString()
               ?: ""
    }
}