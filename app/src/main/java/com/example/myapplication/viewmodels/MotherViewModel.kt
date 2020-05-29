package com.example.myapplication.viewmodels

import androidx.annotation.CallSuper
import com.example.myapplication.domain.DispatcherProvider
import com.example.myapplication.isCancelled
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

abstract class MotherViewModel<T : MotherViewModel.ViewState, S : MotherViewModel.UiAction>(
        private val dispatcherProvider: DispatcherProvider
) : LifecycleReceiver {

    interface ViewState
    interface UiAction

    protected abstract var lastViewState: T
    protected var coroutineScope: CoroutineScope = CoroutineScope(dispatcherProvider.main + SupervisorJob())
    private var viewStatePublisher: ConflatedBroadcastChannel<T> = ConflatedBroadcastChannel()

    override fun onLifecycle(lifecycle: LifecycleReceiver.Lifecycle) {
        when (lifecycle) {
            LifecycleReceiver.Lifecycle.Attach -> onAttach()
            LifecycleReceiver.Lifecycle.Detach -> onDetach()
        }
    }

    @CallSuper
    protected open fun onAttach() {
        if (coroutineScope.isCancelled()) {
            coroutineScope = CoroutineScope(dispatcherProvider.main + SupervisorJob())
        }
        emitViewState(lastViewState)
    }

    @CallSuper
    protected open fun onDetach() {
        coroutineScope.cancel()
    }

    @CallSuper
    open fun onAction(action: S) {

    }

    protected fun emitViewState(newViewState: T) {
        lastViewState = newViewState
        viewStatePublisher.offer(newViewState)
    }

    fun viewStateStream(): Flow<T> {
        return viewStatePublisher.asFlow()
                .flowOn(dispatcherProvider.main)
    }
}
