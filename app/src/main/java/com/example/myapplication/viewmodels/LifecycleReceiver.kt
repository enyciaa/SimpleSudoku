package com.example.myapplication.viewmodels

interface LifecycleReceiver {

    sealed class Lifecycle {
        object Attach : Lifecycle()
        object Detach : Lifecycle()
    }

    fun onLifecycle(lifecycle: Lifecycle)
}
