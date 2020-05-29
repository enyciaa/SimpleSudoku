package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.ui.base.MotherView
import kotlinx.android.synthetic.main.number_picker_view.view.*
import kotlinx.android.synthetic.main.sudoku_square.view.*

class NumberPickerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : MotherView(
        context,
        attrs,
        defStyleAttr
) {

    init {
        inflate(getContext(), R.layout.number_picker_view, this)
        inflateNumbers()
    }

    private fun inflateNumbers() {
        numberPickerLayout.removeAllViews()
        (1..9).forEach {
            val view = inflate(context, R.layout.selectable_number, null)
            numberPickerLayout.addView(view)
            val button = view.findViewById<Button>(R.id.selectableNumber)
            button.text = it.toString()
        }
    }
}