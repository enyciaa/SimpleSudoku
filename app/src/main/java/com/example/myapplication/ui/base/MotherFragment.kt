package com.example.myapplication.ui.base

import android.content.Context
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.example.myapplication.domain.DispatcherProvider
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

abstract class MotherFragment : Fragment(),
                                MotherUiOrchestrator {

    @Inject override lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    @Inject lateinit var dispatcherProvider: DispatcherProvider
    override var coroutineScope: CoroutineScope = CoroutineScope(dispatcherProvider.main + SupervisorJob())

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        attachUiOrchestrator()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        detachUiOrchestrator()
    }
}
