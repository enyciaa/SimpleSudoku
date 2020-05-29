package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import com.example.myapplication.R
import com.example.myapplication.ui.base.MotherView
import kotlinx.android.synthetic.main.number_picker_view.view.*

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
            val numberView = inflate(context, R.layout.selectable_number, numberPickerLayout)
            val numberLayout = numberPickerLayout.getChildAt(it - 1) as FrameLayout
            val button = numberLayout.getChildAt(0) as Button

            // Should work but always returns the first child of the flex layout - ?
            // val button = numberView.findViewById<Button>(R.id.selectableNumber)

            button.text = it.toString()
        }
    }
}