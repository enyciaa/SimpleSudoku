package com.example.myapplication.ui.base

import com.example.myapplication.isCancelled
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.*

/**
 * Contains all the logic and state that is common between Ui containers
 * these ui containers can be activities, fragments, dialog, views,
 * or indeed anything else with a ui and a lifecycle
 *
 * - [attachUiOrchestrator] and [detachUiOrchestrator] always need called from the ui  container
 * - Contains all required dagger logic so no need to extend dagger super classes
 * - Contains coroutine scope which is recreated on each [attachUiOrchestrator] and stopped on each [detachUiOrchestrator]
 */
interface MotherUiOrchestrator : HasAndroidInjector {

    var androidInjector: DispatchingAndroidInjector<Any?>
    var coroutineScope: CoroutineScope

    abstract fun lifecycleReceivers(): List<LifecycleReceiver>

    fun attachUiOrchestrator() {
        if (coroutineScope.isCancelled()) {
            coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        }
        lifecycleReceivers().forEach { it.onLifecycle(LifecycleReceiver.Lifecycle.Attach) }
    }

    fun detachUiOrchestrator() {
        lifecycleReceivers().forEach { it.onLifecycle(LifecycleReceiver.Lifecycle.Detach) }
        coroutineScope.cancel()
    }

    override fun androidInjector(): AndroidInjector<Any?>? {
        return androidInjector
    }
}