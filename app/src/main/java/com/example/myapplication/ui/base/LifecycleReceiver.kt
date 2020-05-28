package com.example.myapplication.ui.base

interface LifecycleReceiver {

    sealed class Lifecycle {
        object Attach : Lifecycle()
        object Detach : Lifecycle()
    }

    fun onLifecycle(lifecycle: Lifecycle)
}
