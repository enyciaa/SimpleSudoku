package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.domain.DispatcherProvider
import com.example.myapplication.ui.base.LifecycleReceiver
import com.example.myapplication.ui.base.MotherUiOrchestrator
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

class SudokuActivity : AppCompatActivity(),
                       MotherUiOrchestrator {

    @Inject override lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun lifecycleReceivers(): List<LifecycleReceiver> {
        return emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sudoku_activity)
    }
}