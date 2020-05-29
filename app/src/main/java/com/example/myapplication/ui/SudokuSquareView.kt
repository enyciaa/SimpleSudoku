package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.ui.base.MotherView
import kotlinx.android.synthetic.main.sudoku_square.view.*

class SudokuSquareView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : MotherView(
        context,
        attrs,
        defStyleAttr
) {

    private lateinit var sudokuSquare: Array<Array<FrameLayout>>

    init {
        this.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        )
        inflate(getContext(), R.layout.sudoku_square, this)
        inflateSquare()
    }

    private fun inflateSquare() {
        sudokuSquare = Array(3) {
            Array(3) {
                val view = inflate(context, R.layout.sudoku_cell, null) as FrameLayout
                sudokuSquareLayout.addView(view)
                view
            }
        }
    }
}