package com.example.myapplication.ui.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import kotlinx.coroutines.*

abstract class MotherView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(
        context,
        attrs,
        defStyleAttr
) {

    protected lateinit var coroutineScope: CoroutineScope

    init {
        this.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
        )
    }

    @CallSuper
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (! this::coroutineScope.isInitialized || coroutineScope.coroutineContext[Job]?.isCancelled != false) {
            coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        }
    }

    @CallSuper
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        coroutineScope.cancel()
    }
}

