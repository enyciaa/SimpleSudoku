package com.example.myapplication.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
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

    private var callback: ((number: Int) -> Unit)? = null

    init {
        inflate(getContext(), R.layout.number_picker_view, this)
        inflateNumbers()
    }

    private fun inflateNumbers() {
        numberPickerLayout.removeAllViews()
        (1..9).forEach { number ->
            val view = inflate(context, R.layout.selectable_number, null)
            numberPickerLayout.addView(view)
            val button = view.findViewById<Button>(R.id.selectableNumber)
            button.setOnClickListener { callback?.invoke(number) }
            button.text = number.toString()
        }
    }

    fun setNumberClickedCallback(callback: (number: Int) -> Unit) {
        this.callback = callback
    }
}