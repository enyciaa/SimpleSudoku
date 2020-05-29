package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.viewmodels.LifecycleReceiver
import com.example.myapplication.viewmodels.MotherUiOrchestrator
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.sudoku_activity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

class SudokuActivity : AppCompatActivity(),
                       MotherUiOrchestrator {

    @Inject override lateinit var androidInjector: DispatchingAndroidInjector<Any?>
    override var coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    val rootView: View by lazy { rootLayoutInSystemWindows }

    override fun lifecycleReceivers(): List<LifecycleReceiver> {
        return emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sudoku_activity)
    }
}